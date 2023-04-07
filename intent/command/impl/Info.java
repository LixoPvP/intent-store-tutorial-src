package intent.command.impl;

import intent.Client;
import intent.command.Command;
import intent.modules.Module;

public class Info extends Command {

	public Info() {
		super("Info", "Displays client information.", "info", "i");
	}

	@Override
	public void onCommand(String[] args, String command) {
		Client.addChatMessage("v" + Client.version + " by Nefarious");
	}
	
}
