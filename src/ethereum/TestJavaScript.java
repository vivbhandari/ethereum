package ethereum;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class TestJavaScript {
	public static void main(String args[]) throws ScriptException, IOException, NoSuchMethodException {
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		engine.eval(Files.newBufferedReader(
				Paths.get("/Users/vivb/eclipse/workspace/practice/src/ethereum/web3Example.js"),
				StandardCharsets.UTF_8));
		Invocable inv = (Invocable) engine;
		Object i = inv.invokeFunction("calc", 5, 6);
		System.out.println(i);
//		Object balance = inv.invokeFunction("getBalance");
//		System.out.println(balance);
	}
}
