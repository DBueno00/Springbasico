package br.com.springbasico.springbasico.web.clients.dtos;

import br.com.springbasico.springbasico.core.models.Client;
import br.com.springbasico.springbasico.core.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientViewModel {
    
    private Long id;

    private String name;

    private String phone;

    private String email;

    public String getPhone(){
        return StringUtils.formatPhone(phone);
    }
    public static ClientViewModel of(Client client){
        return ClientViewModel.builder()
        .id(client.getId())
        .name(client.getName())
        .email(client.getEmail())
        .phone(client.getPhone())
        .build();
    }
}
