package br.com.logos.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

    @GetMapping("/bGltcGEtby1jYWNoZS1kYS1hcGktYWU")
    @CacheEvict(value = "categories", allEntries = true)
    @ResponseStatus(HttpStatus.OK)
    public String invalidCache(){
        return "Cache limpo com sucesso";
    }
}
