package com.example.alfaBankTask.controller;



import com.example.alfaBankTask.service.GifService;
import com.example.alfaBankTask.service.RatesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  private RatesService ratesService;
  private GifService gifService;
    @Value("${giphy.rich}")
  private String richTag;
    @Value("${giphy.broke}")
  private String brokeTag;
    @Value("${giphy.zero}")
    private String zeroTag;

    public MyController(RatesService ratesService, GifService gifService) {
        this.ratesService = ratesService;
        this.gifService = gifService;
    }
    @GetMapping("/getgif/{code}")
     public ResponseEntity getGif(@PathVariable String code) {
        String gifTag=null;
           int gifKey = ratesService.getKeyForMarker(code);

        switch (gifKey) {
            case 1:
                gifTag = this.richTag;
                break;
            case -1:
                gifTag = this.brokeTag;
                break;
            case 0:
                gifTag = this.zeroTag;
                break;
        }

        String result = gifService.getGif(gifTag).toString();
        String url=null;
        int position;
        String[]urlSerch=result.split(",");
        url=urlSerch[3];
        String newFU=url.substring(5);
       return ResponseEntity.ok().body(String.format("<a href=\"%s\">%s</a>",newFU ,gifTag));

    }
}
