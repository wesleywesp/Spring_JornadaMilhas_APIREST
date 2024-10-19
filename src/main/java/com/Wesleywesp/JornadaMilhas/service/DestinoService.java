package com.Wesleywesp.JornadaMilhas.service;




import com.Wesleywesp.JornadaMilhas.domain.Destino;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptionsBuilder;
import org.springframework.stereotype.Service;


@Service
public class DestinoService {
    private final ChatClient chatClient;

    private  DestinoService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultOptions(ChatOptionsBuilder.builder()
                        .withModel("gpt-4o-mini")
                        .build()).
                build();
    }

    private String exemplo(String dados){
        var exemplo= "Exemplo:" +
                "Faça um resumo sobre  enfatizando o Nova York porque este lugar é incrível." +
                "Utilize uma linguagem informal e até 100 caracteres"+
                "no máximo em cada parágrafo. Crie 2 parágrafos neste resumo.";
        return exemplo;
    }


    public String descritivoAi(Destino dados) {
        if(dados.getDescritivo() != null){
            return dados.getDescritivo();
        }
        var system = exemplo(dados.getNome());
        System.out.println(system);
            var gptresposta = gerarDescritivo(system, dados.getNome());
            System.out.println(gptresposta);
            return gptresposta;

    }
    public String gerarDescritivo(String system, String descritivo ){
        return this.chatClient.prompt()
                .system(system)
                .user("faça uma descrição sobre "+ descritivo)
                .options(ChatOptionsBuilder.builder()
                        .withTemperature(1.0)
                        .build())
            .advisors(new SimpleLoggerAdvisor())
            .call()
                .content();
}
}
