package com.ruoyi.web.controller.DS;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ruoyi.DS.domain.*;
import com.ruoyi.DS.service.*;
import com.ruoyi.DS.utils.DSUtils;
import com.ruoyi.DS.utils.JsonUtil;
import com.ruoyi.DS.utils.SkuCodeBuilder;
import com.ruoyi.common.utils.security.PermissionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;


/**
 * 商品Controller
 *
 * @author ruoyi
 * @date 2019-12-26
 */

@Transactional(rollbackFor = Exception.class)
@Controller
@RequestMapping("/DS/product")
public class ProductController extends BaseController
{
    private String prefix = "DS/product";


    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private ISupplierService iSupplierService;

    @Autowired
    private ISkuproductService skuproductService;



    @RequiresPermissions("DS:product:view")
    @GetMapping()
    public String product(Model model)
    {
//        List<Category> list1 = iCategoryService.selectCategoryList(null);
        List<Supplier> list2 = iSupplierService.selectSupplierList(null);
//        model.addAttribute("list1",list1);
        model.addAttribute("list2",list2);
        return prefix + "/product";
    }

    /**
     * 查询商品列表
     */
    @RequiresPermissions("DS:product:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Product product)
    {
        startPage();
        List<Product> list = productService.selectProductList(product);
        for (Product p:list
             ) {
            String filename = p.getFilepath();
            String[] number = filename.split("-");
            p.setFilepath(number[0]);
        }
        return getDataTable(list);
    }

    /**
     * 导出商品列表
     */
    @RequiresPermissions("DS:product:export")
    @Log(title = "商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Product product)
    {
        List<Product> list = productService.selectProductList(product);
        ExcelUtil<Product> util = new ExcelUtil<Product>(Product.class);
        return util.exportExcel(list, "product");

    }

    /**
     * 新增商品
     */
    @GetMapping("/add")
    public String add(Model model)
    { return prefix + "/add";}

    /**
     * 新增保存商品
     */
    @RequiresPermissions("DS:product:add")
    @Log(title = "商品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("filepathes")String[] files, @RequestParam(value = "skuMessages", required = false) String[] skuMessages, Product product) throws ParseException {
             //商品保存
            String fileName = "";
            List<String> list = new ArrayList<String>();
            Collections.addAll(list, files);
            for (String filename:list
            ) {
                fileName = fileName + filename + '-';
            }
            product.setFilepath(fileName);
            Date date = new Date();
            product.setCreateTime(date);
            product.setUpdateTime(date);
            String userName = (String) PermissionUtils.getPrincipalProperty("userName");
            product.setCreateBy(userName);
            product.setUpdateBy("暂未更新");
            productService.insertProduct(product);
            //sku单品保存
            for (int i = 1; i < skuMessages.length; i++) {
                Skuproduct skuproduct =  JsonUtil.fromJson(skuMessages[i],  Skuproduct.class);
                String code = SkuCodeBuilder.getSkuCode(product.getCode(), skuproductService);
                skuproduct.setCode(code);
                skuproduct.setProduct(product.getId());
                skuproductService.insertSkuproduct(skuproduct);
            }

            return toAjax(true);
    }


    /**
     * 修改商品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {

        Product product = productService.selectProductById(id);
        mmap.put("product", product);
        return prefix + "/edit";
    }


    /**
     * 修改保存商品
     */
    @RequiresPermissions("DS:product:edit")
    @Log(title = "商品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestParam("filepathes")String[] files, @RequestParam(value = "skuMessages", required = false) String[] skuMessages,Product product) throws IOException {
//        商品更新
        String fileName =new String();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < files.length ; i++) {
            list.add(files[i]);
        }
        for (String filename:list
        ) {
            fileName = fileName + filename + '-';
        }
        product.setFilepath(fileName);
        Date date = new Date();
        String userName = (String) PermissionUtils.getPrincipalProperty("userName");
        product.setUpdateBy(userName);
        product.setUpdateTime(date);
        Long productId = product.getId();
        productService.updateProduct(product);
        //sku单品判断是否存在进行添加/更新
        if (skuMessages != null){
            for (int i = 1; i < skuMessages.length; i++) {
                Skuproduct skuproduct =  JsonUtil.fromJson(skuMessages[i],  Skuproduct.class);
                if (skuproduct.getId() == null){
                    // 上传文件路径
                    String code = SkuCodeBuilder.getSkuCode(product.getCode(), skuproductService);
                    skuproduct.setCode(code);
                    skuproduct.setProduct(productId);
                    skuproductService.insertSkuproduct(skuproduct);
                }else {
                    skuproductService.updateSkuproduct(skuproduct);
                }

            }
        }

        return toAjax(true);
    }


    /**
     * 删除商品
     */
    @RequiresPermissions("DS:product:remove")
    @Log(title = "商品", businessType = BusinessType.DELETE)
    @PostMapping( value = "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        String[] args = ids.split(",");
        if (args.length != 1){
            for (int i = 0; i < args.length; i++) {
                Product product = productService.selectProductById(Long.parseLong(args[i]));
                Skuproduct skuproduct = new Skuproduct();
                skuproduct.setCode(product.getCode().toString());
                if (skuproductService.selectSkuproductList(skuproduct).size() == 0)
                    return toAjax(productService.deleteProductByIds(ids));
                else
                    return error("fail to delete"+args[i]+"has 'sku'");
            }
            return toAjax(productService.deleteProductByIds(ids));
        }else {
            Product product = productService.selectProductById(Long.parseLong(ids));
            Skuproduct skuproduct = new Skuproduct();
            skuproduct.setCode(product.getCode().toString());
            if (skuproductService.selectSkuproductList(skuproduct).size() == 0)
                return toAjax(productService.deleteProductByIds(ids));
            else
                return error("删除失败，改商品存在sku单品");
        }
        }



    /*
     ajax获取 option列表
  */
    @GetMapping("/refresh")
    @ResponseBody
    public Map<String, List> add(){
        Map data = new HashMap();
        data.put("supplier", iSupplierService.selectSupplierList(new Supplier()));
        return data;
    }


    /**
     * 获取和商品关联的sku商品
     * @param id
     * @return
     */
    @PostMapping("/getSkuList")
    @ResponseBody
    public List<String> getSkuList(@RequestParam("id") Long id){
        List<String> list = new ArrayList<String>();
        Product product = productService.selectProductById(id);
        Skuproduct skuproduct = new Skuproduct();
        skuproduct.setCode(String.valueOf(product.getCode()));
        List<Skuproduct> list1 = skuproductService.selectSkuproductList(skuproduct);
        for (Skuproduct sku: list1
             ) {
            if (sku.getSalesStatTime() != null){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = format.format(sku.getSalesStatTime());
                sku.setRemark(str);
            }
            list.add(JsonUtil.toJson(sku));
        }
        return list;
    }

    /**
     * 获取商品图片列表
     * @param id
     * @return
     */
    @PostMapping("/getImgList")
    @ResponseBody
    public List<String> getImgList(String id){
        List<String> list = new ArrayList<String>();
        Product product = productService.selectProductById(Long.parseLong(id));
        String filepath = product.getFilepath();
        String[] filegroup =  filepath.split("-");
        for (String sub:filegroup
        ) {
            list.add(sub);
        }
        return list;
    }



    /**
     * 保存文件 返回文件路径
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/getFileName")
    @ResponseBody
    public List<String> getFileName(@RequestParam("file") MultipartFile[] file) throws IOException {
        List list = new ArrayList<String>();
        for (int i = 0; i < file.length; i++) {
            String filename = DSUtils.getFileName(file[i]);
            list.add(filename);
        }
        return list;
    }



    /**
     * 获取商品富文本描述信息
     * @param id
     * @return
     */

    @PostMapping("/getDistribute")
    @ResponseBody
    public String getDistribute(String id){
        Product product = productService.selectProductById(Long.parseLong(id));
        return product.getDd();
    }



    /**
     * 获取仓库,物流渠道name
     * @return
     */
    @GetMapping("/getName/{id}")
    @ResponseBody
    public Map<String, String > getName(@PathVariable Integer id){
        Map<String , String > data = new HashMap<>();
        Product product = productService.selectProductById(Long.parseLong(id.toString()));
        Long supplier = product.getSupplier();
        Long category = product.getCategory();
        data.put("supplier", iSupplierService.selectSupplierById(supplier).getName());
        data.put("category", iCategoryService.selectCategoryById(category).getName());
        return data;
    }
}
