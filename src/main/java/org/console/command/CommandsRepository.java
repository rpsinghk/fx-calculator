package org.console.command;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CommandsRepository {
	
	
	enum CommandType 
    { 
        EXIT, PARSE; 
    }

	private final static ConcurrentHashMap<String, Command> commandMap = new ConcurrentHashMap<String, Command>();

    
    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }
    
    public Optional<?> execute(String commandString) {
    	
        Command command = commandMap.get(commandString);
        if (command == null) {
        	command = commandMap.get("parse");
        }
        return command.execute(commandString);
    }

}
