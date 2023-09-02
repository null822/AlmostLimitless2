package com.null8.messingaround;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class IntToLongPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {

    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (targetClassName.startsWith("com")) return false;
         return targetClassName.contains("world");
        //return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        fix(targetClass);
    }

    private void fix(ClassNode targetClass) {
        for (FieldNode field : targetClass.fields) {
            Type objectType = Type.getType(field.desc);
            if (objectType.equals(Type.INT_TYPE)) {
                System.out.println("got int");
                field.desc = Type.LONG_TYPE.getDescriptor();
                if (field.value instanceof Integer integer) field.value = (long) integer;
            } else {
                System.out.println(objectType);
            }
        }
        for (MethodNode method : targetClass.methods) {
            Type[] args = Arrays.stream(Type.getArgumentTypes(method.desc)).map(a -> a.equals(Type.INT_TYPE) ? Type.LONG_TYPE : a).toList().toArray(new Type[0]);
            Type returnType = Type.getReturnType(method.desc);
            method.desc = Type.getMethodDescriptor(returnType.equals(Type.INT_TYPE) ? Type.LONG_TYPE : returnType, args);

            var a = method.instructions.iterator();
            while (a.hasNext()) {
                AbstractInsnNode node = a.next();
                if (node.getOpcode() == Opcodes.IRETURN && !returnType.equals(Type.BOOLEAN_TYPE)) {
                    a.remove();
                    a.add(new InsnNode(Opcodes.LRETURN));
                }
                if (node.getOpcode() == Opcodes.SIPUSH) {
                    IntInsnNode node1 = (IntInsnNode) node;
                    int op = node1.operand;
                    a.remove();

                    a.add(new LdcInsnNode((long)op));
                }

                if (node.getOpcode() == Opcodes.ILOAD) {
                    VarInsnNode node1 = (VarInsnNode) node;
                    a.remove();

                    a.add(new VarInsnNode(Opcodes.LLOAD, node1.var));
                }
            }
        }
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
