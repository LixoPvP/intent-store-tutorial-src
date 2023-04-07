package intent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import intent.command.CommandManager;
import intent.events.Event;
import intent.events.listeners.EventChat;
import intent.events.listeners.EventKey;
import intent.modules.Module;
import intent.modules.Module.Category;
import intent.modules.combat.KillAura;
import intent.modules.movement.*;
import intent.modules.player.*;
import intent.modules.render.*;
import intent.ui.HUD;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class Client {
	
	public static String name = "Tutorial", version = "1";
	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	public static HUD hud = new HUD();
	public static CommandManager commandManager = new CommandManager();
	
	public static void startup(){
		System.out.println("Starting " + name  + " - v" + version);
		Display.setTitle(name + " v" + version);
		
		modules.add(new Fly());
		modules.add(new Sprint());
		modules.add(new FullBright());
		modules.add(new NoFall());
		modules.add(new TabGUI());
		modules.add(new KillAura());
	}
	
	public static void onEvent(Event e){
		if(e instanceof EventChat) {
			commandManager.handleChat((EventChat)e);
		}
		
		for(Module m : modules){
			if(!m.toggled)
				continue;
			
			m.onEvent(e);
		}
	}
	
	public static void keyPress(int key){
		Client.onEvent(new EventKey(key));
		
		for(Module m : modules){
			if(m.getKey() == key){
				m.toggle();
			}
		}
	}
	
	public static  List<Module> getModulesByCategory(Category c){
		List<Module> modules = new ArrayList<Module>();
		
		for(Module m : Client.modules) {
			if(m.category == c)
				modules.add(m);
		}
		
		return modules;
	}
	
	public static void addChatMessage(String message) {
		message = "\2479" + name + "\2477: " + message;
		
		Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
	}

}
