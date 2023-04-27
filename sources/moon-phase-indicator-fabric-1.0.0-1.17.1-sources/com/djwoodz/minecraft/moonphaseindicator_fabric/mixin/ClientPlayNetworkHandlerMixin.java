package com.djwoodz.minecraft.moonphaseindicator_fabric.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.djwoodz.minecraft.moonphaseindicator_fabric.event.StatsHandler;
import net.minecraft.class_3468;
import net.minecraft.class_634;

@Mixin(class_634.class)
public class ClientPlayNetworkHandlerMixin {
    @Inject(method = "onStatistics(Lnet/minecraft/network/packet/s2c/play/StatisticsS2CPacket;)V", at = @At("HEAD"))
    private void onStatistics(net.minecraft.class_2617 packet, CallbackInfo info) {
        if (packet != null) {
            Integer integer = packet.method_11273().get(class_3468.field_15419.method_14956(class_3468.field_15429));
            if (integer != null) {
                StatsHandler.setTimeAwake(integer.intValue());
            }
        }
    }
}
