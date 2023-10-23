package ru.appline;

import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class Controller {
    public Map<String, String> mapa = new HashMap<String, String>();

    @PostMapping(value = "/add", consumes = "application/json")
    public void add(@RequestBody Map<String, String> a)
    {
        mapa.putAll(a);
    }

    //если прописывать id  в body
    /*@GetMapping(value = "/get", consumes = "application/json", produces = "application/json")
    public Map<String,String> get(@RequestBody Map<String,Integer> mapSide)
    {
        String side="";
        for(Map.Entry<String, String> entry: mapa.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            int[] valueInt= convert(value);
            int mapVal=mapSide.get("Degree");
            if(mapVal>=valueInt[0]&&mapVal<=valueInt[1])
                side=key;
        }
        Map<String,String> mapResp=new HashMap<String, String>();
        mapResp.put("Side",side);
        return mapResp;
    }*/

    //если прописывать id  в строке
    @GetMapping(value = "/get", produces = "application/json")
    public Map<String,String> get(@RequestParam("Degree") int degree)
    {
        String side="";
        for(Map.Entry<String, String> entry: mapa.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            int[] valueInt= convert(value);
            if(degree>=valueInt[0]&&degree<=valueInt[1])
                side=key;
        }
        Map<String,String> mapResp=new HashMap<String, String>();
        mapResp.put("Side",side);
        return mapResp;
    }


    int[] convert(String str)
    {
        String[] part=str.split("-");
        int one=Integer.parseInt(part[0]);
        int two=Integer.parseInt(part[1]);
        if(two<one) {
            int temp=one;
            one = two;
            two=temp;
        }
        int[] a={one,two};
        return a;
    }

}
