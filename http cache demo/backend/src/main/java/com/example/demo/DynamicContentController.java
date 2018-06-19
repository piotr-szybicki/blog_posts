package com.example.demo;

import com.example.demo.datamodel.CachedObject;
import com.example.demo.service.HardToComputeStringService;
import com.example.demo.service.LongStringService;
import com.example.demo.service.WeCantCacheThisStringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.concurrent.TimeUnit.SECONDS;

@RestController
@RequestMapping("/bookmarks")
public class DynamicContentController {

    @Autowired
    private LongStringService longStringService;

    @Autowired
    private HardToComputeStringService hardToComputeStringService;

    @Autowired
    private WeCantCacheThisStringService weCantCacheThisStringService;


    @GetMapping("/longString")
    ResponseEntity<String> getThatSuperLongString(){
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(0, SECONDS))
                .body("{ \"field\": \""+longStringService.changingIrregularly()+"\"}");
    }

    @GetMapping("/hardToComputeString")
    ResponseEntity<String> getThatSuperhardToComputeString(){

        CachedObject hardToCompute = hardToComputeStringService.hardToCompute();

        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.maxAge(hardToCompute.validInSeconds(), SECONDS))
                .body("{ \"field\": \""+hardToCompute.payload() +"\"}");
    }

    @GetMapping("/clearCache")
    ResponseEntity<String> clearCache(){
        longStringService.clearCache();
        return ResponseEntity.ok("resource deleted successfully");
    }

    @GetMapping("/nonCacheableSpring")
    ResponseEntity<String> nonCachableString(){
        return ResponseEntity
                .ok()
                .cacheControl(CacheControl.noStore().noTransform().mustRevalidate())
                .body("{ \"field\": \""+ weCantCacheThisStringService.nonCachableString() +"\"}");
    }
}
