package com.ultima.entrega;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//Para incluir el servidor remoto en la lista de serividores agrego las siguiente lineas de swagger
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API REST Facturacion",
				version = "v1",
				description = "API para manejar operaciones de facturacion con clientes , productos y ventas"
		),
		servers = {
				@Server(url = "https://apirestemer-production.up.railway.app", description = "Servidor de Producción"),
				@Server(url = "http://localhost:8080", description = "Servidor Local")
		}
)
public class EntregaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntregaApplication.class, args);
	}

}
