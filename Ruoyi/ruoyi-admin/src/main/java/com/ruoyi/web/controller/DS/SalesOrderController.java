package com.ruoyi.web.controller.DS;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.DS.domain.*;
import com.ruoyi.DS.service.*;
import com.ruoyi.DS.service.impl.RandomImpl;
import com.ruoyi.DS.utils.DSUtils;
import com.ruoyi.DS.utils.JsonUtil;
import com.ruoyi.DS.utils.OrderNumberBuilder;
import com.ruoyi.common.utils.security.PermissionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
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
import org.springframework.web.client.RestTemplate;

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
    private IInventoryService inventoryService;

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
        BigDecimal totalPurchase = new BigDecimal(0);
        BigDecimal totalCost = new BigDecimal(0);
        salesOrder1.setTotalCost(new BigDecimal(0));
        salesOrderService.insertSalesOrder(salesOrder1);
        //order_sku信息保存
        if (orderSku != null) {
            for (int i = 1; i < orderSku.length; i++) {
                OrderSku orderSku1 = JsonUtil.fromJson(orderSku[i], OrderSku.class);
                /**
                 * 库存扣减
                 */
                if (salesOrder1.getOrderStatus() == 3){
                    Inventory inventory = inventoryService.selectInventoryBySKU(orderSku1.getProductSkuId());
                    inventory.setAllocated(inventory.getAllocated() + orderSku1.getQuantity());
                    inventory = DSUtils.refreshInventory(inventory);
                    Skuproduct skuproduct = skuproductService.selectSkuproductById(inventory.getSku());
                    skuproduct.setPurchasePlan(inventory.getOosq().toString());
                    inventoryService.updateInventory(inventory);
                    skuproductService.updateSkuproduct(skuproduct);

                }
                orderSku1.setSalesOrderId(salesOrder1.getId());
                orderSku1.setPlatformOrderItemId(salesOrder1.getSaleId());
                totalWeight = totalWeight.add(new BigDecimal(orderSku1.getWeight()*orderSku1.getQuantity()));
                totalPurchase = totalPurchase.add(new BigDecimal(orderSku1.getPurchaseCost()*orderSku1.getQuantity()));
                totalCost = totalCost.add(new BigDecimal(orderSku1.getQuantity()*orderSku1.getPrice() + orderSku1.getShipping()));
                iOrderSkuService.insertOrderSku(orderSku1);
            }
        }
        salesOrder1.setTotalWeight(totalWeight);
        salesOrder1.setTotalPurchase(totalPurchase);
        salesOrder1.setTotalCost(totalCost);
        salesOrderService.updateSalesOrder(salesOrder1);
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
        BigDecimal totalPurchase = new BigDecimal(0);
        BigDecimal totalCost = new BigDecimal(0);
        // order_sku信息保存
        if (orderSku != null) {
            for (int i = 1; i < orderSku.length; i++) {
                OrderSku orderSku1 = JsonUtil.fromJson(orderSku[i], OrderSku.class);
                OrderSku orderSku2 = iOrderSkuService.selectOrderSkuById(orderSku1.getId());
                if(orderSku1.getId()==null) {
                    orderSku1.setSalesOrderId(salesOrder1.getId());
                    orderSku1.setPlatformOrderItemId(salesOrder1.getSaleId());
                    orderSku1.setSalesOrderId(salesOrder1.getId());
                    // 库存扣减
                    if (salesOrder1.getOrderStatus() == 3 ){
                        Inventory inventory = inventoryService.selectInventoryBySKU(orderSku1.getProductSkuId());
                        inventory.setAllocated(inventory.getAllocated() + orderSku1.getQuantity());
                        inventory = DSUtils.refreshInventory(inventory);
                        Skuproduct skuproduct = skuproductService.selectSkuproductById(inventory.getSku());
                        skuproduct.setPurchasePlan(inventory.getOosq().toString());
                        inventoryService.updateInventory(inventory);
                        skuproductService.updateSkuproduct(skuproduct);

                    }
                    totalWeight = totalWeight.add(new BigDecimal(orderSku1.getWeight()*orderSku1.getQuantity()));
                    totalPurchase = totalPurchase.add(new BigDecimal(orderSku1.getPurchaseCost()*orderSku1.getQuantity()));
                    totalCost = totalCost.add(new BigDecimal(orderSku1.getQuantity()*orderSku1.getPrice() + orderSku1.getShipping()));
                    iOrderSkuService.insertOrderSku(orderSku1);
                    break;
                }
                if(orderSku1.getId()!=null) {
                    if (orderSku1.getQuantity() != orderSku2.getQuantity()){
                        Inventory inventory = inventoryService.selectInventoryBySKU(orderSku1.getProductSkuId());
                        inventory.setAllocated(inventory.getAllocated() - orderSku2.getQuantity() + orderSku1.getQuantity());
                        inventory = DSUtils.refreshInventory(inventory);
                        Skuproduct skuproduct = skuproductService.selectSkuproductById(inventory.getSku());
                        skuproduct.setPurchasePlan(inventory.getOosq().toString());
                        inventoryService.updateInventory(inventory);
                        skuproductService.updateSkuproduct(skuproduct);
                    }
                    totalWeight = totalWeight.add(new BigDecimal(orderSku1.getWeight()*orderSku1.getQuantity()));
                    totalPurchase = totalPurchase.add(new BigDecimal(orderSku1.getPurchaseCost()*orderSku1.getQuantity()));
                    totalCost = totalCost.add(new BigDecimal(orderSku1.getQuantity()*orderSku1.getPrice() + orderSku1.getShipping()));
                    iOrderSkuService.updateOrderSku(orderSku1);
                }
            }
        }
        salesOrder1.setTotalWeight(totalWeight);
        salesOrder1.setTotalPurchase(totalPurchase);
        salesOrder1.setTotalCost(totalCost);
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
    public List<Map<Integer, String>> getProductImgSrc(@PathVariable Integer id) {
        List<Map<Integer, String>> data = new ArrayList<Map<Integer, String>>();
        OrderSku orderSku = new OrderSku();
        orderSku.setSalesOrderId(id);
        if ( iOrderSkuService.selectOrderSkuList(orderSku).size() != 0){
            for (OrderSku orderSku1: iOrderSkuService.selectOrderSkuList(orderSku)) {
                Integer orderskuId = orderSku1.getProductSkuId();
                Skuproduct skuproduct = skuproductService.selectSkuproductById(orderskuId);
                Map<Integer, String> imgMessage = new HashMap<>();
                imgMessage.put(Integer.parseInt(skuproduct.getProduct().toString()), skuproduct.getFilepath());
                data.add(imgMessage);
            }
        }else {
            data.add(null);
        }
        return data;
    }





    /**
     * 获取仓库,物流渠道列表
     * @return
     */
    @GetMapping("/refresh")
    @ResponseBody
    public Map<String, List> refreshWareHouse() {
        Map<String, List> data = new HashMap<String, List>();
        data.put("warehouse", warehouseService.selectWarehouseList(new Warehouse()));
        data.put("logisticsChannel",logisticsChannelService.selectLogisticsChannelList(new LogisticsChannel()));
        data.put("shippingAddress", shippingAddressService.selectShippingAddressList(new ShippingAddress()));
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
        List<Skuproduct> data = new ArrayList<Skuproduct>();
        Skuproduct skuproduct = new Skuproduct();
        List<Skuproduct> skuproductList = skuproductService.selectSkuproductList(skuproduct);
        for (Skuproduct sku:
             skuproductList) {
            if (sku.getStatus() != 0){
                data.add(sku);
            }
        }
        return data;
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
