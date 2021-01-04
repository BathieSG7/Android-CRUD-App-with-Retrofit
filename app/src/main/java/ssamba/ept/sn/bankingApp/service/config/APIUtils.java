package ssamba.ept.sn.bankingApp.service.config;


import ssamba.ept.sn.bankingApp.service.AgenceService;
import ssamba.ept.sn.bankingApp.service.ClientService;
import ssamba.ept.sn.bankingApp.service.CompteService;

public class APIUtils {

    private APIUtils(){
    };

    public static final String API_URL = "http://10.0.2.2:9090/";

    public static ClientService getClientService(){
        return RetrofitClient.getClient(API_URL).create(ClientService.class);
    }

    public static AgenceService getAgenceService(){
        return RetrofitClient.getClient(API_URL).create(AgenceService.class);
    }

    public static CompteService getCompteService(){
        return RetrofitClient.getClient(API_URL).create(CompteService.class);
    }

}