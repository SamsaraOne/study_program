package com.lx.eduService.controller;


import com.lx.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("/login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("/info")
    public R getInfo(){


        return R.ok().data("roles","admin").data("name","lisli").
                data("avator","https://www.baidu.com/link?url=pPGuPOXPqYabHvUQ6tqb3rgsOqabgBaYUymVrxiyZhEjATU7QPy0-aAAID5uqQrqW33MGShPyZlYTGzRrAuxnljZbxbWVniVzEzIH9QSE0lsOt_Rr3IVPx-kR48yu44rZBSiYWMyTsqdiZKsoICvzjmafEamDOO1psmFdeQp9PPtDCa5xbQspu114JKrYoYIqqAnl80shDNCuR_kc8zxAwOgToEWcxhUVzcdh7hGNZ5viLXT9yFWywEntQJvd9_8BWlX6wLsuTrI9uRdp0yNtByvEkcLWgGohJd2l7IA2yklgdhvg8pHinTkrXeJ30bqcpgPAICF2q38IjXzYv65nBMeKv24QudIiEzSDTXOhb2Hf4lyfZxQKXULYQj5CaAdw_V_AVFdjJkZIKertXMDz_hljqjnPjhJaYm3306ZALUe5ZZRNzb84WY59hrvhwi5lfeLm8PQ12_ORBBZXHm_CGlJsZmp9hxqisZZzverH2_LkChBqFlBJIGdNgSeaB4faZBfdBlT5szlYB3MLWbP0fkCcrNRzJ5QqNkNL6Q4yu4iCUXIb7Ol4Pht7GJkrX0xEH_3VXG1ykfD5m1Bwbi6r-ZafQXykVKtSjoEG8yEzrxOpTWDrSzZUnq6kubb8eRCi53Au-EZ8v1HuLC7YmBQJ_&timg=https%3A%2F%2Fss0.bdstatic.com%2F94oJfD_bAAcT8t7mm9GUKT-xh_%2Ftimg%3Fimage%26quality%3D100%26size%3Db4000_4000%26sec%3D1604929604%26di%3D10af0b9d6592455bf7bb3b2cd41faf03%26src%3Dhttp%3A%2F%2Fimages.669pic.com%2Felement_pic%2F43%2F57%2F83%2F1%2F3ed3001c3e5cdcc467b8d4f0dfe7d8df.jpg!w700wb&click_t=1604929608302&s_info=1903_937&wd=&eqid=b3b384fb0007870e000000055fa94843");
    }
}
