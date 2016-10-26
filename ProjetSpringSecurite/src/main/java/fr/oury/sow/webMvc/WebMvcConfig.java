package fr.oury.sow.webMvc;


@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry){
		
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/logout").setViewName("login");
		
	}
}

