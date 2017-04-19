package indra.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Classe responsavel pela automatização de troca de senha no browser
 * 
 * @author tabatista
 * @version 1.0
 *
 */
public class Driver {

	public static WebDriver driver;
	public static Robot robot;

	public Driver() {

		configurarDriver();
	}

	/** ----------------------MÉTODOS GENÉRICOS---------------------- **/

	public void configurarDriver() {

		try {
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			options.addArguments("--disable-extensions");

			// options.addArguments("--privileged");
			// options.addArguments("--test-type");
			// options.addArguments("ignore-certificate-errors");

			capabilities.setCapability("chrome.binary", "<<your chrome path>>");
			capabilities.setCapability("profile.content_settings.pattern_pairs.,.multiple-automatic-downloads", 1);
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);

			// para tirar a sugestao de save password do driver
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			// -------- //

			driver = new ChromeDriver(capabilities);
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void matarProcessos() throws IOException {
		Process process = Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
		Scanner leitor = new Scanner(process.getInputStream());
		while (leitor.hasNextLine()) {
			System.out.println(leitor.nextLine());
		}

		leitor.close();
	}

	public static void aguardarCarregamentoPagina(WebDriver driver) {

		new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

	public void encerrarDriver() {
		// Driver.configuraTeclado();
		driver.quit();
	}

	public static void configuraTeclado() throws AWTException {
		robot = new Robot();
		robot.keyPress(KeyEvent.VK_SHIFT);
		robot.keyPress(KeyEvent.VK_ALT);

		robot.keyRelease(KeyEvent.VK_SHIFT);
		robot.keyRelease(KeyEvent.VK_ALT);

	}

	/**
	 * Metodo responsavel por logar e trocar a senha da indra
	 * 
	 * @param usuario
	 *            o usuario utilizado pela pessoa
	 * @param senha
	 *            a atual senha - que será mantida depois dos procedimentos
	 * @throws Exception
	 */
	public void trocarSenhaParaAtual(String usuario, String senha) throws Exception {

		// entra na page de troca de senha
		driver.get("https://apps.indraweb.net/usuario/CambioClave.jsp");

		// Aguarda o carregamento da pagina
		aguardarCarregamentoPagina(driver);

		/**
		 * LOGIN
		 */

		// limpa campos caso estejam preenchidos
		driver.findElement(By.xpath("//*[@name=\"login\"]")).clear();
		driver.findElement(By.xpath("//*[@name=\"passwd\"]")).clear();

		// envia os dados do login
		driver.findElement(By.xpath("//*[@name=\"login\"]")).sendKeys(usuario);
		driver.findElement(By.xpath("//*[@name=\"passwd\"]")).sendKeys(senha);
		Thread.sleep(2000);

		// clica no botão de login
		driver.findElement(By.xpath("//*[@id=\"btnSubmitForm\"]")).click();

		/**
		 * / FORMULARIO DE TROCA DE SENHA
		 */

		// senhas geradas
		List<String> passwords = Driver.gerarSenhasGenericasUseless();

		// robo para manipular o teclado
		robot = new Robot();

		trocarSenhaformulario(senha, passwords.get(0));

		// loop para as demais senhas
		for (int i = 1; i < passwords.size(); i++) {

			trocarSenhaformulario(passwords.get(i - 1), passwords.get(i));
		}

		/*
		 * Troca de volta a antiga-atual senha
		 */

		trocarSenhaformulario(passwords.get(3), senha);

	}

	/**
	 * Metodo responsavel por gerar as senhas que serão descartadas
	 * posteriormente
	 * 
	 * @return lista size = 4, contendo 4 senhas genericas
	 */

	public static List<String> gerarSenhasGenericasUseless() {

		List<String> senhas = new ArrayList<>();

		for (int ultimoNumero = 1; ultimoNumero <= 4; ultimoNumero++) {

			senhas.add("SenhaG@00" + ultimoNumero);
			// System.out.println("Senha gerada: " + senhas.get(ultimoNumero -
			// 1));
		}

		return senhas;
	}

	/**
	 * Metodo responsavel por ler os campos da pagina de troca de senha e trocar
	 * a senha
	 * 
	 * @param senhaAntiga
	 *            a antiga-atual senha para ser trocada
	 * @param novaSenha
	 *            a senha no lugar da atual
	 * @throws InterruptedException
	 */
	public void trocarSenhaformulario(String senhaAntiga, String novaSenha) throws InterruptedException {

		// senha antiga
		driver.findElement(By.xpath("//*[@name=\"old\"]")).clear();
		driver.findElement(By.xpath("//*[@name=\"old\"]")).sendKeys(senhaAntiga);

		// nova senha
		driver.findElement(By.xpath("//*[@name=\"new1\"]")).clear();
		driver.findElement(By.xpath("//*[@name=\"new1\"]")).sendKeys(novaSenha);

		// repete nova senha
		driver.findElement(By.xpath("//*[@name=\"new2\"]")).clear();
		driver.findElement(By.xpath("//*[@name=\"new2\"]")).sendKeys(novaSenha);

		// confirma troca com enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		Thread.sleep(2000);

		// enter para tirar o alerta
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		System.out.println("Senha alterada para: " + novaSenha);

		Thread.sleep(3000);
	}

}
