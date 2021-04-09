package net.vicnix.staff;

import net.vicnix.staff.command.Command;
import net.vicnix.staff.command.LocateTeleportCommand;
import net.vicnix.staff.command.VanishCommand;
import net.vicnix.staff.session.Session;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {

    private final static CommandManager instance = new CommandManager();

    private final Map<String, Command> commandMap = new HashMap<>() {{
        put("vanish", new VanishCommand());
        put("ltp", new LocateTeleportCommand());
    }};

    public static CommandManager getInstance() {
        return instance;
    }

    public void executeCommand(Session session, String label, String[] args) {
        if (session == null) return;

        Command command = this.commandMap.get(label);

        if (command == null) {
            return;
        }

        command.execute(session, args);
    }
}