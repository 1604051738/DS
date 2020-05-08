package com.ruoyi.web.controller.DS;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.DS.domain.*;
import com.ruoyi.DS.service.*;
import com.ruoyi.DS.service.impl.RandomImpl;
import com.ruoyi.DS.utils.JsonUtil;
import com.ruoyi.DS.utils.OrderNumberBuilder;
import com.ruoyi.common.utils.security.PermissionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 销售订单Controller
 * 
 * @author admin
 * @date 2020-04-23
 */
@Controller
@RequestMapping("/DS/sales_order")
public class SalesOrderController extends BaseController
{
    private String prefix = "DS/sales_order";

    @Autowired
    private ISalesOrderService salesOrderService;

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private RandomImpl random;

    @Autowired
    private ILogisticsChannelService logisticsChannelService;

    @Autowired
    private IOrderSkuService iOrderSkuService;

    @Autowired
    private ISkuproductService skuproductService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IShippingAddressService shippingAddressService;

    @RequiresPermissions("DS:sales_order:view")
    @GetMapping()
    public String sales_order()
    {
        return prefix + "/sales_order";
    }

    /**
     * 查询销售订单列表
     */
    @RequiresPermissions("DS:sales_order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SalesOrder salesOrder)
    {
        startPage();
        List<SalesOrder> list = salesOrderService.selectSalesOrderList(salesOrder);
        return getDataTable(list);
    }

    /**
     * 导出销售订单列表
     */
    @RequiresPermissions("DS:sales_order:export")
    @Log(title = "销售订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SalesOrder salesOrder)
    {
        List<SalesOrder> list = salesOrderService.selectSalesOrderList(salesOrder);
        ExcelUtil<SalesOrder> util = new ExcelUtil<SalesOrder>(SalesOrder.class);
        return util.exportExcel(list, "sales_order");
    }

    /**
     * 新增销售订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }


    /**
     * 新增保存销售订单
     */
    @RequiresPermissions("DS:sales_order:add")
    @Log(title = "销售订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult addSave(@RequestParam("salesOrderMessage") String salesOrder, @RequestParam(value = "orderSkuMessage",required = false)String[] orderSku)
    {
        /**
         * salesorder订单保存
         * salesorder（销售订单）   json转pojo
         * */
        SalesOrder salesOrder1 = JsonUtil.fromJson(salesOrder, SalesOrder.class);
        String sales_order_Id = OrderNumberBuilder.getOrderNumber();
        OrderNumberBuilder.setRANDOMNUMER(OrderNumberBuilder.getRANDOMNUMER()+1);
        //设置订单编号
        random.setNumber(OrderNumberBuilder.getRANDOMNUMER());
        salesOrder1.setSaleId(sales_order_Id);
        //自动设置操作者用户名
        String userName = (String) PermissionUtils.getPrincipalProperty("userName");
        salesOrder1.setCreateBy(userName);
        BigDecimal totalWeight = new BigDecimal(0);
//        order_sku信息保存
        if (orderSku != null) {
            for (int i = 1; i < orderSku.length; i++) {
                OrderSku orderSku1 = JsonUtil.fromJson(orderSku[i], OrderSku.class);
                orderSku1.setSalesOrderId(salesOrder1.getId());
                orderSku1.setPlatformOrderItemId(salesOrder1.getSaleId());
                totalWeight = totalWeight.add(new BigDecimal(orderSku1.getWeight()*orderSku1.getQuantity()));
                iOrderSkuService.insertOrderSku(orderSku1);
            }
        }
        salesOrder1.setTotalWeight(totalWeight);
        salesOrderService.insertSalesOrder(salesOrder1);
        return toAjax(true);
    }

    /**
     * 修改销售订单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SalesOrder salesOrder = salesOrderService.selectSalesOrderById(id);
        mmap.put("salesOrder", salesOrder);
        return prefix + "/edit";
    }

    /**
     * 修改保存销售订单
     */
    @RequiresPermissions("DS:sales_order:edit")
    @Log(title = "销售订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult editSave(@RequestParam("salesOrderMessage") String salesOrder, @RequestParam(value = "orderSkuMessage",required = false)String[] orderSku)
    {
        SalesOrder salesOrder1 = JsonUtil.fromJson(salesOrder, SalesOrder.class);
        BigDecimal totalWeight = new BigDecimal(0);
        //        order_sku信息保存
        if (orderSku != null) {
            for (int i = 1; i < orderSku.length; i++) {
                OrderSku orderSku1 = JsonUtil.fromJson(orderSku[i], OrderSku.class);
                if(orderSku1.getId()==null) {
                    orderSku1.setSalesOrderId(salesOrder1.getId());
                    orderSku1.setPlatformOrderItemId(salesOrder1.getSaleId());
                    orderSku1.setSalesOrderId(salesOrder1.getId());
                    totalWeight = totalWeight.add(new BigDecimal(orderSku1.getWeight()*orderSku1.getQuantity()));
                    iOrderSkuService.insertOrderSku(orderSku1);
                }
                if(orderSku1.getId()!=null) {
                    totalWeight = totalWeight.add(new BigDecimal(orderSku1.getWeight()*orderSku1.getQuantity()));
                    iOrderSkuService.updateOrderSku(orderSku1);
                }
            }
        }
        salesOrder1.setTotalWeight(totalWeight);
        salesOrderService.updateSalesOrder(salesOrder1);
        return toAjax(true);
    }

    /**
     * 删除销售订单
     */
    @RequiresPermissions("DS:sales_order:remove")
    @Log(title = "销售订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        String[] args = ids.split(",");
        if (args.length != 1){
            for (int i = 0; i < args.length; i++) {
                SalesOrder salesOrder = salesOrderService.selectSalesOrderById(Integer.parseInt(args[i]));
                OrderSku orderSku = new OrderSku();
                orderSku.setSalesOrderId(salesOrder.getId());
                for (OrderSku orderSku2:
                        iOrderSkuService.selectOrderSkuList(orderSku)) {
                    iOrderSkuService.deleteOrderSkuById(orderSku2.getId());
                }
                salesOrderService.deleteSalesOrderByIds(ids);
            }
        }else {
            SalesOrder salesOrder = salesOrderService.selectSalesOrderById(Integer.parseInt(ids));
            OrderSku orderSku = new OrderSku();
            orderSku.setSalesOrderId(salesOrder.getId());
            for (OrderSku orderSku1:
            iOrderSkuService.selectOrderSkuList(orderSku)) {
                iOrderSkuService.deleteOrderSkuById(orderSku1.getId());
            }
            salesOrderService.deleteSalesOrderByIds(ids);
        }
        return toAjax(true);
    }



    /**
     * 获取销售订单所关联的单品项
     */
    @PostMapping("/getOrderSkuList")
    @ResponseBody
    public List<OrderSku> getOrderSkuList(@RequestParam("id")Integer id){
        List<OrderSku> orderSkuList = new ArrayList<OrderSku>();
        OrderSku orderSku = new OrderSku();
        orderSku.setSalesOrderId(id);
        orderSkuList = iOrderSkuService.selectOrderSkuList(orderSku);
        return orderSkuList;
    }



    /**
     * 获取商品图片
     */
    @GetMapping("/getProductMessage/{id}")
    @ResponseBody
    synchronized
    public List<String> getProductImgSrc(@PathVariable Integer id) {
        List<String> data = new ArrayList<String>();
//        SalesOrder salesOrder = salesOrderService.selectSalesOrderById(id);
        OrderSku orderSku = new OrderSku();
        orderSku.setSalesOrderId(id);
        if ( iOrderSkuService.selectOrderSkuList(orderSku).size() != 0){
            Integer skuid = iOrderSkuService.selectOrderSkuList(orderSku).get(0).getProductSkuId();
            Skuproduct skuproduct = skuproductService.selectSkuproductById(skuid);
            data.add(skuproduct.getProduct().toString());
            for (OrderSku orderSku1: iOrderSkuService.selectOrderSkuList(orderSku)) {
                Integer orderskuId = orderSku1.getProductSkuId();
                data.add(skuproductService.selectSkuproductById(orderskuId).getFilepath());
            }
        }else {
            data.add("0");
        }
        return data;
    }





    /**
     * 获取仓库,物流渠道列表
     * @return
     */
    @GetMapping("/refresh")
    @ResponseBody
    public Map<Integer, List> refreshWareHouse() {
        Map<Integer, List> data = new HashMap<Integer, List>();
        data.put(1, warehouseService.selectWarehouseList(new Warehouse()));
        data.put(2,logisticsChannelService.selectLogisticsChannelList(new LogisticsChannel()));
        data.put(3, shippingAddressService.selectShippingAddressList(new ShippingAddress()));
        return data;
    }


    /**
     * 获取仓库,物流渠道name
     * @return
     */
    @GetMapping("/getName/{id}")
    @ResponseBody
    public Map<String, String > getName(@PathVariable Integer id){
        Map<String , String > data = new HashMap<>();
        SalesOrder salesOrder = salesOrderService.selectSalesOrderById(id);
        Long channel = salesOrder.getLogisticsChannel();
        Long warehouse = salesOrder.getWarehouse();
        Long address = salesOrder.getAddress();
        data.put("warehouse", warehouseService.selectWarehouseById(warehouse).getChinesename());
        data.put("channel", logisticsChannelService.selectLogisticsChannelById(channel).getLogisticsName());
        data.put("address", shippingAddressService.selectShippingAddressById(address).getName());
        return data;
    }

    /**
     * edit页面 选中skucode自动补全sku信息
     * @param skuCode
     * @return
     */
    @GetMapping("/setOrderSkuMessage/{skuCode}")
    @ResponseBody
    public Skuproduct setOrderSkuMessage(@PathVariable String skuCode){
        return skuproductService.selectSkuproductByCode(skuCode);
    }

    /**
     * 获取所有skuproduct
     * @return
     */
    @GetMapping("/getSkuList")
    @ResponseBody
    public List<Skuproduct> getSkuList(){
        return skuproductService.selectSkuproductList(new Skuproduct());
    }


    /**
     * 获取收货人信息
     */
    @PostMapping(value = "/getAddress")
    @ResponseBody
    public ShippingAddress getAddress(@RequestParam(value = "id") Integer id){
        ShippingAddress data = new ShippingAddress();
        SalesOrder salesOrder = salesOrderService.selectSalesOrderById(id);
        data = shippingAddressService.selectShippingAddressById(salesOrder.getAddress());
        return data;
    }
}
