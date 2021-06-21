package tr.com.teb.prmreactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class PrmController {
	@Autowired
	private WebClient.Builder webClientBuilder;

//	@Autowired
//	private ConfigProperties configProperties;

	@Value("${prm.url}")
	private String url;

	@GetMapping("/prm")
	public Mono<String> prmGetTest() {
		Mono<String> response = webClientBuilder.build().get().uri(url).exchangeToMono(resp -> callBackForGet(resp));
		return response;
	}

	@PostMapping("/prm")
	public Mono<String> prmPostTest() {
		Mono<String> response = webClientBuilder.build().get().uri(url).exchangeToMono(resp -> callBackForPost(resp));
		return response;
	}

	@GetMapping("/ok")
	public Mono<String> prmGetEmpty() {
		return Mono.just("ok");
	}

	@PostMapping("/ok")
	public Mono<String> prmPostEmpty() {
		return Mono.just("ok");
	}

	private Mono<String> callBackForGet(ClientResponse response) {
		return response.bodyToMono(String.class);
	}

	private Mono<String> callBackForPost(ClientResponse response) {
		return response.bodyToMono(String.class);
	}

}
