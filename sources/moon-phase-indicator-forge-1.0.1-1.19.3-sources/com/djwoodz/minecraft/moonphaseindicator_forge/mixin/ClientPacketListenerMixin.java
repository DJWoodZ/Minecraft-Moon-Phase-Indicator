package com.djwoodz.minecraft.moonphaseindicator_forge.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.djwoodz.minecraft.moonphaseindicator_forge.event.StatsHandler;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.stats.Stats;

@Mixin(ClientPacketListener.class)
public class ClientPacketListenerMixin {
    @Inject(method = "handleAwardStats(Lnet/minecraft/network/protocol/game/ClientboundAwardStatsPacket;)V", at = @At("HEAD"))
    private void handleAwardStats(net.minecraft.network.protocol.game.ClientboundAwardStatsPacket packet, CallbackInfo info) {
        if (packet != null) {
            Integer integer = packet.getStats().get(Stats.CUSTOM.get(Stats.TIME_SINCE_REST));
            if (integer != null) {
                StatsHandler.setTimeAwake(integer.intValue());
            }
        }
    }
}
