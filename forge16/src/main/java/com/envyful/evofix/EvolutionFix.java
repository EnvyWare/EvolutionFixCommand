package com.envyful.evofix;

import com.envyful.api.config.yaml.YamlConfigFactory;
import com.envyful.api.forge.command.ForgeCommandFactory;
import com.envyful.evofix.command.EvoFixCommand;
import com.envyful.evofix.config.EvoFixLocale;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;

@Mod(value = EvolutionFix.MOD_ID)
public class EvolutionFix {

    public static final String MOD_ID = "evofix";

    private static EvolutionFix instance;

    private final ForgeCommandFactory commandFactory = new ForgeCommandFactory();

    private EvoFixLocale locale;

    public EvolutionFix() {
        instance = this;
        MinecraftForge.EVENT_BUS.register(this);

        this.reloadConfig();
    }


    @SubscribeEvent
    public void registerCommand(RegisterCommandsEvent event) {
        this.commandFactory.registerCommand(event.getDispatcher(), new EvoFixCommand());
    }

    public void reloadConfig() {
        try {
            this.locale = YamlConfigFactory.getInstance(EvoFixLocale.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static EvoFixLocale getLocale() {
        return instance.locale;
    }
}
