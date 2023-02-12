package com.envyful.evofix.config;

import com.envyful.api.config.data.ConfigPath;
import com.envyful.api.config.yaml.AbstractYamlConfig;
import com.google.common.collect.Lists;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.List;

@ConfigSerializable
@ConfigPath("config/EvolutionFix/locale.yml")
public class EvoFixLocale extends AbstractYamlConfig {

    private List<String> invalidArguments = Lists.newArrayList(
            "&c&l(!) &cInsufficient args. Do /evofix <slot>"
    );

    private List<String> invalidSlot = Lists.newArrayList(
            "&c&l(!) &cInvalid slot. /evofix <slot>"
    );

    private List<String> cannotEvolveThatPokemon = Lists.newArrayList(
            "&c&l(!) &cCannot evolve that pokemon!"
    );

    private List<String> errorOccurredWhileSpawning = Lists.newArrayList(
            "&c&l(!) &cAn error occurred while throwing out your Pokemon. Please try again"
    );

    private List<String> evolved = Lists.newArrayList(
            "&e&l(!) &eSuccessfully evolved %pokemon%"
    );

    private List<String> noEvolutions = Lists.newArrayList(
            "&c&l(!) &cNo evolutions were found that %pokemon% matches the requirements for"
    );

    public EvoFixLocale() {
        super();
    }

    public List<String> getInvalidArguments() {
        return this.invalidArguments;
    }

    public List<String> getInvalidSlot() {
        return this.invalidSlot;
    }

    public List<String> getCannotEvolveThatPokemon() {
        return this.cannotEvolveThatPokemon;
    }

    public List<String> getErrorOccurredWhileSpawning() {
        return this.errorOccurredWhileSpawning;
    }

    public List<String> getEvolved() {
        return this.evolved;
    }

    public List<String> getNoEvolutions() {
        return this.noEvolutions;
    }
}
