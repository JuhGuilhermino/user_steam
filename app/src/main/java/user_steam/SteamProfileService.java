package user_steam;

import com.lukaspradel.steamapi.core.exception.SteamApiException;
import com.lukaspradel.steamapi.data.json.playersummaries.GetPlayerSummaries;
import com.lukaspradel.steamapi.data.json.playersummaries.Player;
import com.lukaspradel.steamapi.webapi.client.SteamWebApiClient;
import com.lukaspradel.steamapi.webapi.request.GetPlayerSummariesRequest;
import com.lukaspradel.steamapi.webapi.request.builders.SteamWebApiRequestFactory;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SteamProfileService {
    private String apiKey = ""; // Inserir chave da API da Steam
    private List<String> steamIds = new ArrayList<>();
    private SteamWebApiClient client = new SteamWebApiClient.SteamWebApiClientBuilder(apiKey).build();
    private GetPlayerSummariesRequest request;
    private String data = "";

    SteamProfileService() {}

    public void setId(String id){
        steamIds.add(id);
        request = SteamWebApiRequestFactory.createGetPlayerSummariesRequest(steamIds);
    }

    // Retorno o nome do usuário
    public String getRealName(){
        try {
            GetPlayerSummaries response = client.<GetPlayerSummaries> processRequest(request);
            if (!response.getResponse().getPlayers().isEmpty()) {
                Player player = response.getResponse().getPlayers().get(0);
                data = player.getPersonaname();
            } else {
                return "Nenhum jogador encontrado com esse ID.";
            } 
        } catch (SteamApiException e) {
            e.printStackTrace();
        }
        return data;
    }

    // Retorno a URL do perfil
    public String getProfileURL(){
        try {
            GetPlayerSummaries response = client.<GetPlayerSummaries> processRequest(request);
            if (!response.getResponse().getPlayers().isEmpty()) {
                Player player = response.getResponse().getPlayers().get(0);
                data = player.getProfileurl();
            } else {
                return "Nenhum jogador encontrado com esse ID.";
            } 
        } catch (SteamApiException e) {
            e.printStackTrace();
        }
        return data;
    }

    // Retorno o estado do usário
    public String getState(){
        try {
            GetPlayerSummaries response = client.<GetPlayerSummaries> processRequest(request);
            if (!response.getResponse().getPlayers().isEmpty()) {
                Player player = response.getResponse().getPlayers().get(0);
                data = player.getPersonastate() == 1 ? "Online" : "Offline";
            } else {
                return "Nenhum jogador encontrado com esse ID.";
            } 
        } catch (SteamApiException e) {
            e.printStackTrace();
        }
        return data;
    }   
    
     // Retorno o estado do usário
    public String getAvatar(){
        try {
            GetPlayerSummaries response = client.<GetPlayerSummaries> processRequest(request);
            if (!response.getResponse().getPlayers().isEmpty()) {
                Player player = response.getResponse().getPlayers().get(0);
                data = player.getAvatarmedium();
            } else {
                return "Nenhum jogador encontrado com esse ID.";
            } 
        } catch (SteamApiException e) {
            e.printStackTrace();
        }
        return data;
    } 
}
