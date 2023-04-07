package intent.modules.movement;

import org.lwjgl.input.Keyboard;

import intent.events.Event;
import intent.events.listeners.EventUpdate;
import intent.modules.Module;

public class Fly extends Module {
	
	public Fly(){
		super("Fly", Keyboard.KEY_G, Category.MOVEMENT);
	}
	
	public void onDisable(){
		mc.thePlayer.capabilities.isFlying = false;
	}
	
	public void onEvent(Event e){
		if(e instanceof EventUpdate){
			if(e.isPre()){
				mc.thePlayer.capabilities.isFlying = true;
			}
		}
	}

}
