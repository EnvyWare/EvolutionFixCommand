package com.envyful.evofix;

import com.envyful.api.forge.command.ForgeCommandFactory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod(value = EvolutionFix.MOD_ID)
public class EvolutionFix {

    public static final String MOD_ID = "evofix";

    private final ForgeCommandFactory commandFactory = new ForgeCommandFactory();

    public EvolutionFix() {
        MinecraftForge.EVENT_BUS.register(this);
    }


    @SubscribeEvent
    public void registerCommand(RegisterCommandsEvent event) {

    }

}
