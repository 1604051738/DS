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
import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUploadUtils;
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

import javax.servlet.http.HttpServletRequest;

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
        List<Category> list1 = iCategoryService.selectCategoryList(null);
        List<Supplier> list2 = iSupplierService.selectSupplierList(null);
        model.addAttribute("list1",list1);
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
            for (int i = 1; i < skuMessages.length; i++) {
                Skuproduct skuproduct =  JsonUtil.fromJson(skuMessages[i],  Skuproduct.class);
                String code = SkuCodeBuilder.getSkuCode(skuproduct.getProduct(), skuproductService);
                // 上传文件路径
                skuproduct.setSkucode(code);
                skuproductService.insertSkuproduct(skuproduct);
            }
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
            return toAjax(productService.insertProduct(product));
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
        if (skuMessages != null){
            for (int i = 1; i < skuMessages.length; i++) {
                Skuproduct skuproduct =  JsonUtil.fromJson(skuMessages[i],  Skuproduct.class);
                if (skuproduct.getSkucode().equals("")){
                    String code = SkuCodeBuilder.getSkuCode(skuproduct.getProduct(), skuproductService);
                 // 上传文件路径
                    skuproduct.setSkucode(code);
                    skuproductService.insertSkuproduct(skuproduct);
                }else {
                    skuproductService.updateSkuproduct(skuproduct);
                }

            }
        }
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
        return toAjax(productService.updateProduct(product));
    }


    /**
     * 删除商品
     */
    @RequiresPermissions("DS:product:remove")
    @Log(title = "商品", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        String[] args = ids.split(",");
        if (args.length != 1){
            for (int i = 0; i < args.length; i++) {
                Product product = productService.selectProductById(Long.parseLong(args[i]));
                Skuproduct skuproduct = new Skuproduct();
                skuproduct.setSkucode(product.getCode().toString());
                if (skuproductService.selectSkuproductList(skuproduct).size() == 0)
                    return toAjax(productService.deleteProductByIds(ids));
                else
                    return error("删除失败，所选的"+args[i]+"号商品存在关联的sku单品项");
            }
            return toAjax(productService.deleteProductByIds(ids));
        }else {
            Product product = productService.selectProductById(Long.parseLong(ids));
            Skuproduct skuproduct = new Skuproduct();
            skuproduct.setSkucode(product.getCode().toString());
            if (skuproductService.selectSkuproductList(skuproduct).size() == 0)
                return toAjax(productService.deleteProductByIds(ids));
            else
                return error("删除失败，该商品存在关联的sku单品项");
        }
        }



    /*
     ajax获取 option列表
  */
    @GetMapping("/refresh")
    @ResponseBody
    public List<Map<Long, String>> add(){
        List<Map<Long, String>> list = new ArrayList<Map<Long, String>>();
        Map<Long, String> category =  new HashMap<Long, String>();
        Map<Long, String> supplier =  new HashMap<Long, String>();
        List<Category> categories = iCategoryService.selectCategoryList(null);
        List<Supplier> suppliers = iSupplierService.selectSupplierList(null);
        for (Category i:
             categories) {
            category.put(i.getId(), i.getName());
        }
        for (Supplier i:
             suppliers) {
            supplier.put(i.getId(), i.getName());
        }
        list.add(category);
        list.add(supplier);
        return list;
    }




    @PostMapping("/getSkuList")
    @ResponseBody
    public List<String> getSkuList(@RequestParam("id") Long id){
        List<String> list = new ArrayList<String>();
        Product product = productService.selectProductById(id);
        Skuproduct skuproduct = new Skuproduct();
        skuproduct.setSkucode(String.valueOf(product.getCode()));
        List<Skuproduct> list1 = skuproductService.selectSkuproductList(skuproduct);
        for (Skuproduct sku: list1
             ) {
            if (sku.getStt() != null){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String str = format.format(sku.getStt());
                sku.setRemark(str);
            }
            list.add(JsonUtil.toJson(sku));
        }
        return list;
    }

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

    /*
        保存sku与product信息
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/saveskuandproduct")
    public void saveskuandproduct(){

    }
}
