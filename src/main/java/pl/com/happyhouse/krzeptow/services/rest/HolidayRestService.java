package pl.com.happyhouse.krzeptow.services.rest;


import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.com.happyhouse.krzeptow.model.rest.HolidayRest;

import java.util.List;

@Service
public class HolidayRestService {

    private final String KEY = "d5b3f4bdb4msh6333a839574534dp1de800jsn389c2485d0ec";
    private final String HOST = "public-holiday.p.rapidapi.com";

    public List<HolidayRest> getHolidaysInYear(int year) {
        RestTemplate restTemplate = new RestTemplate();
        String url = String.format("https://public-holiday.p.rapidapi.com/%d/PL", year);
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", KEY);
        headers.set("X-RapidAPI-Host", HOST);
        HttpEntity<List<HolidayRest>> entity = new HttpEntity<>(headers);


        HttpEntity<List<HolidayRest>> exchange = restTemplate.exchange(
                url, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});
        return exchange.getBody();
    }
}
