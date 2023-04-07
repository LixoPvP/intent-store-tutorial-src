package intent.modules.player;

import org.lwjgl.input.Keyboard;

import intent.events.Event;
import intent.events.listeners.EventUpdate;
import intent.modules.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module {
	
	public NoFall(){
		super("NoFall", Keyboard.KEY_M, Category.PLAYER);
	}
	
	public void onEvent(Event e) {
		if(e instanceof EventUpdate && e.isPre()) {
			if(mc.thePlayer.fallDistance > 2)
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
		}
	}

}
