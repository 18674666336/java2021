package com.glongmen.coursedesign.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.glongmen.coursedesign.Vo.GoodsVo;
import com.glongmen.coursedesign.Vo.OrdersVo;
import com.glongmen.coursedesign.entity.Goods;
import com.glongmen.coursedesign.Vo.Result;
import com.glongmen.coursedesign.entity.Order;
import com.glongmen.coursedesign.entity.Token;
import com.glongmen.coursedesign.service.impl.GoodsServiceImpl;
import com.glongmen.coursedesign.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author ：Ariel~~
 * @description：TODO
 * @date ：2020/12/26 17:25
 */

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsServiceImpl goodsService;

    @Autowired
    RedisUtils redisUtils;

    /**
     * @param :
     * @return java.util.List<com.glongmen.coursedesign.entity.Goods>
     * @description: TODO 查询所有商品
     * @author Ariel~~
     * @date 12:48 2020/12/27
     */
    @GetMapping("/selectGoods")
    public List<Goods> selectGoods() {
        List<Goods> allGoods = goodsService.findAllGoods();
        return allGoods;
    }

    /**
     * @param id: 删除的商品id
     * @return com.glongmen.coursedesign.Vo.Result
     * @description: TODO删除商品
     * @author Ariel~~
     * @date 12:48 2020/12/27
     */
    @DeleteMapping("/deleted/{id}")
    public Result deleteGoods(@PathVariable("id") int id) {
        int i = goodsService.deleteGoodsById(id);
        if (i == 1) {
            return new Result(200, "删除成功!");
        } else {
            return new Result(500, "已经被删除!");
        }
    }

    /**
     * @param name:  商品名称
     * @param price: 商品价格
     * @param stock: 商品库存
     * @param type:  商品类型
     * @param value: 选择的商品地址代码
     * @param desc:  商品描述
     * @return com.glongmen.coursedesign.Vo.Result
     * @description: TODO 添加商品
     * @author Ariel~~
     * @date 18:56 2020/12/28
     */
    @PostMapping("/insertGoods/{name}/{price}/{stock}/{value}/{type}/{desc}")
    public Result insertGoods(@PathVariable String name, @PathVariable double price, @PathVariable int stock, @PathVariable int type, @PathVariable int value, @PathVariable String desc) {
        Goods newGoods = new Goods();
        newGoods.setName(name);
        newGoods.setPrice(price);
        newGoods.setStock(stock);
        newGoods.setType(type);
        newGoods.setPointId(value);
        newGoods.setContent(desc);
        newGoods.setPicPath("1609144094006.jpg");
        int i = goodsService.insertGoods(newGoods);
        if (i != 0) {
            return new Result(200, "商品添加成功!", i);
        } else {
            return new Result(500, "商品添加失败!");
        }
    }

    /**
     * @param currentPage: 分页查询的页数
     * @param pageSize:    分页查询每页显示的数量
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.glongmen.coursedesign.entity.Goods>
     * @description: TODO
     * @author Ariel~~
     * @date 21:22 2020/12/27
     */
    @GetMapping("/pagingquery/{type}/{currentPage}/{pageSize}/{point}")
    public Page<GoodsVo> pagingQuery(@PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize, @PathVariable int type, @PathVariable int point) {
        Page<GoodsVo> page = new Page<>(currentPage, pageSize);
        return goodsService.pagingQuery(page, type, point);
    }

    /**
     * @param currentPage:查询的页码
     * @param state:查询订单的状态
     * @param pageSize:查询的每页大小
     * @return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.glongmen.coursedesign.Vo.OrdersVo>
     * @description: TODO 分页查询订单
     * @author Ariel~~
     * @date 16:00 2020/12/30
     */
    @GetMapping("/ordersQuery/{state}/{currentPage}/{pageSize}/{pointID}")
    public Page<OrdersVo> orderQuery(@PathVariable int currentPage, @PathVariable int state, @PathVariable int pageSize, @PathVariable int pointID) {
        Page<OrdersVo> objectPage = new Page<>(currentPage, pageSize);
        return goodsService.findOrderByState(objectPage, state, pointID);
    }


    /**
     * @param file: 传过来的文件参数
     * @return com.glongmen.coursedesign.Vo.Result
     * @description: TODO 文件上传并重新命名(记得后期使用UUID命名)
     * @author Ariel~~
     * @date 18:55 2020/12/28
     */
    @PostMapping("/picUpload/{id}")
    public Result picUpload(@RequestParam("file") MultipartFile file, @PathVariable int id) throws IOException {
        //获取文件上传的名称
        String originalFilename = file.getOriginalFilename();
        //对文件进行重命名
        String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));
        System.out.println("新文件名称:"+newFileName);
        //获取服务器发布路径
        String realPath = ResourceUtils.getURL("classpath:").getPath() + "static/imgs/goods";
        System.out.println(realPath);
        //保存文件到:
        try {
            file.transferTo(new File(realPath + "\\" + newFileName));
            int i = goodsService.updatePic(id, newFileName);
            if (i == 1) {
                return new Result(200, "文件上传成功");
            } else
                return new Result(500, "文件上传失败");
        } catch (IOException e) {
            return new Result(500, "文件上传失败");
        } catch (IllegalStateException e) {
            return new Result(500, "文件上传失败");
        }

    }

    /**
     * @param id: 要查询的id
     * @return com.glongmen.coursedesign.Vo.Result<com.glongmen.coursedesign.entity.Goods>
     * @description: TODO 通过id查询商品
     * @author Ariel~~
     * @date 20:48 2020/12/28
     */
    @GetMapping("/findOne/{id}")
    public Result<Goods> findOne(@PathVariable int id) {
        Goods goodsByID = goodsService.findGoodsByID(id);
        if (goodsByID != null) {
            return new Result<Goods>(200, "成功", goodsByID);
        } else {
            return new Result<>(500, "失败");
        }
    }

    /**
     * @param name:  商品名字
     * @param price: 商品价格
     * @param stock: 商品库存
     * @param type:  商品类型
     * @param value: 商品代收
     * @param desc:  商品描述
     * @param id:    商品id
     * @return com.glongmen.coursedesign.Vo.Result
     * @description: TODO修改商品
     * @author Ariel~~
     * @date 21:12 2020/12/28
     */
    @PutMapping("/updateGoods/{id}/{name}/{price}/{stock}/{value}/{type}/{desc}")
    public Result updateGoods(@PathVariable int id, @PathVariable String name, @PathVariable double price, @PathVariable int stock, @PathVariable int type, @PathVariable int value, @PathVariable String desc) {
        Goods newGoods = new Goods();
        newGoods.setId(id);
        newGoods.setName(name);
        newGoods.setPrice(price);
        newGoods.setStock(stock);
        newGoods.setType(type);
        newGoods.setPointId(value);
        newGoods.setContent(desc);
        int i = goodsService.updateGoods(newGoods);
        if (i == 1) {
            return new Result(200, "修改成功!");
        } else {
            return new Result(500, "修改失败!");
        }
    }

    @PostMapping("/pay/{goodsId}/{time}/{pointId}/{numb}/{price}/{token}")
    public Result pay(@PathVariable int goodsId, @PathVariable Date time, @PathVariable String token, @PathVariable int pointId, @PathVariable double price, @PathVariable int numb) {
        Token token1 = redisUtils.findToken(token);
        if (token1 == null) {
            return new Result(500, "下单失败!");
        } else {
            Order order = new Order();
            order.setCustomerId(token1.getId());
            order.setGoodsId(goodsId);
            order.setPointId(pointId);
            order.setSendTime(time);
            order.setNumb(numb);
            price = price * numb;
            order.setMoney(price);
            int i = goodsService.insertOrder(order, numb);
            if (i == 1) {
                return new Result(200, "下单成功!");
            } else {
                return new Result(500, "下单失败!");
            }
        }
    }

    @DeleteMapping("/deletedOrder/{id}")
    public Result deleteOrder(@PathVariable int id) {
        int i = goodsService.deleteOrderById(id);
        if (i == 1) {
            return new Result(200, "删除成功!");
        } else {
            return new Result(500, "删除失败!");
        }

    }


    @PutMapping("/finishOrder/{id}")
    public Result finishOrder(@PathVariable int id) {
        int i = goodsService.finishOrderById(id);
        if (i == 1) {
            return new Result(200, "配送成功!");
        } else {
            return new Result(500, "配送失败!");
        }
    }

    @GetMapping("/count/{id}")
    public Result count(@PathVariable int id) {
        if (id == 0)
            return new Result(200, "获取数据成功", goodsService.countAllOrder());
        else {
            return new Result(200, "获取数据成功", goodsService.countOrderById(id));
        }
    }
}
