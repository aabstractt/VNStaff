package net.vicnix.staff;

import net.vicnix.staff.command.Command;
import net.vicnix.staff.command.LocateTeleportCommand;
import net.vicnix.staff.session.Session;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private final static CommandManager instance = new CommandManager();

    private final Map<String, Command> commandMap = new HashMap<>() {{
        put("ltp", new LocateTeleportCommand());
    }};

    public static CommandManager getInstance() {
        return instance;
    }

    public void register(String name, Command command) {
        this.commandMap.put(name, command);
    }

    public void executeCommand(Session session, String label, String[] args) {
        if (session == null) {
            ConsoleUtils.getInstance().sendMessage("&cRun this command in-game");

            return;
        }

        Command command = this.commandMap.get(label);

        if (command == null) {
            return;
        }

        command.execute(session, args);
    }
}