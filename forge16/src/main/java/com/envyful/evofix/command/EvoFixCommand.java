package com.envyful.evofix.command;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.Permissible;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.type.UtilParse;
import com.envyful.evofix.EvolutionFix;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.stats.evolution.Evolution;
import com.pixelmonmod.pixelmon.api.pokemon.stats.evolution.conditions.EvoCondition;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import com.pixelmonmod.pixelmon.entities.pixelmon.PixelmonEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.Util;

import java.util.List;

@Command(
        value = "evofix",
        description = "Attempt to check your evolution /evofix [slot]",
        aliases = {
                "evolutionfix"
        }
)
@Permissible("com.envyful.command.evofix")
public class EvoFixCommand {

    @CommandProcessor
    public void onCommand(@Sender ServerPlayerEntity player, String[] args) {
        if (args.length == 0) {
            for (String s : EvolutionFix.getLocale().getInvalidArguments()) {
                player.sendMessage(UtilChatColour.colour(s), Util.NIL_UUID);
            }
            return;
        }

        int slot = UtilParse.parseInteger(args[0]).orElse(-1);

        if (slot < 1 || slot > 6) {
            for (String s : EvolutionFix.getLocale().getInvalidSlot()) {
                player.sendMessage(UtilChatColour.colour(s), Util.NIL_UUID);
            }
            return;
        }

        slot--;
        Pokemon pokemon = StorageProxy.getParty(player).getAll()[slot];

        if (pokemon == null ||  pokemon.isEgg()) {
            for (String s : EvolutionFix.getLocale().getCannotEvolveThatPokemon()) {
                player.sendMessage(UtilChatColour.colour(s), Util.NIL_UUID);
            }
            return;
        }

        PixelmonEntity entity = pokemon.getOrCreatePixelmon(pokemon.getOwnerPlayer());

        if (entity == null) {
            for (String s : EvolutionFix.getLocale().getErrorOccurredWhileSpawning()) {
                player.sendMessage(UtilChatColour.colour(s), Util.NIL_UUID);
            }
            return;
        }

        if (this.didEvolve(entity)) {
            for (String s : EvolutionFix.getLocale().getEvolved()) {
                player.sendMessage(UtilChatColour.colour(s), Util.NIL_UUID);
            }
        } else {
            for (String s : EvolutionFix.getLocale().getNoEvolutions()) {
                player.sendMessage(UtilChatColour.colour(s), Util.NIL_UUID);
            }
        }
    }

    private boolean didEvolve(PixelmonEntity entity) {
        for (Evolution evolution : entity.getPokemon().getForm().getEvolutions()) {
            if (!this.doesPassConditions(entity, evolution.conditions) || !entity.testLevelEvolution(entity.getPokemon().getPokemonLevel())) {
                continue;
            }

            return true;
        }

        return false;
    }

    private boolean doesPassConditions(PixelmonEntity entity, List<EvoCondition> conditions) {
        for (EvoCondition condition : conditions) {
            if (!condition.passes(entity)) {
                return false;
            }
        }

        return true;
    }
}
