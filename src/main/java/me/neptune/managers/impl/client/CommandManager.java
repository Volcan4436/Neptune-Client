package me.neptune.managers.impl.client;

import com.google.common.eventbus.Subscribe;
import me.neptune.Neptune;
import me.neptune.api.command.Command;
import me.neptune.api.command.impl.ToggleCommand;
import me.neptune.events.network.PacketEvent;
import me.neptune.managers.util.IterableRegistry;
import me.neptune.util.minecraft.ChatUtil;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements IterableRegistry<Command>
{
    private final List<Command> commands = new ArrayList<>();
    public static String PREFIX = "+";

    public void init() {
        register(new ToggleCommand());

        Neptune.EVENT_BUS.register(this);
    }

    @Subscribe
    public void onChatMessage(PacketEvent.Send event)
    {
        if(event.getPacket() instanceof ChatMessageC2SPacket packet){
            if (packet.chatMessage().startsWith(PREFIX))
            {
                event.setCancelled(true);
                for (Command command : commands)
                {
                    if(packet.chatMessage().substring(1).toLowerCase().contains(command.getName().toLowerCase()))
                    {
                        try {
                            command.run(packet.chatMessage()
                                    .substring(PREFIX.length() + command.getName().length())
                                    .split(" "));
                        } catch (Exception e) {
                            ChatUtil.sendMessage("Failed to run command" + command.getName() + "! " + e.getMessage(), true);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void register(Command object) {
        commands.add(object);
        Neptune.EVENT_BUS.register(object);
    }

    @Override
    public void unregister(Command object) {
        commands.remove(object);
    }

    @Override
    public List<Command> getRegistered() {
        return commands;
    }
}
