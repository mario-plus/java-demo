package com.mario.client.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zxz
 * @date 2023年03月16日 14:35
 */
public class UdpChannelPromise implements ChannelPromise {
    @Override
    public Channel channel() {
        return null;
    }

    @Override
    public ChannelPromise setSuccess(Void result) {
        return null;
    }

    @Override
    public boolean trySuccess(Void aVoid) {
        return false;
    }

    @Override
    public ChannelPromise setSuccess() {
        return null;
    }

    @Override
    public boolean trySuccess() {
        return false;
    }

    @Override
    public ChannelPromise setFailure(Throwable cause) {
        return null;
    }

    @Override
    public boolean tryFailure(Throwable throwable) {
        return false;
    }

    @Override
    public boolean setUncancellable() {
        return false;
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public boolean isCancellable() {
        return false;
    }

    @Override
    public Throwable cause() {
        return null;
    }

    @Override
    public ChannelPromise addListener(GenericFutureListener<? extends Future<? super Void>> listener) {
        return null;
    }

    @Override
    public ChannelPromise addListeners(GenericFutureListener<? extends Future<? super Void>>... listeners) {
        return null;
    }

    @Override
    public ChannelPromise removeListener(GenericFutureListener<? extends Future<? super Void>> listener) {
        return null;
    }

    @Override
    public ChannelPromise removeListeners(GenericFutureListener<? extends Future<? super Void>>... listeners) {
        return null;
    }

    @Override
    public ChannelPromise sync() throws InterruptedException {
        return null;
    }

    @Override
    public ChannelPromise syncUninterruptibly() {
        return null;
    }

    @Override
    public ChannelPromise await() throws InterruptedException {
        return null;
    }

    @Override
    public ChannelPromise awaitUninterruptibly() {
        return null;
    }

    @Override
    public boolean await(long l, TimeUnit timeUnit) throws InterruptedException {
        return false;
    }

    @Override
    public boolean await(long l) throws InterruptedException {
        return false;
    }

    @Override
    public boolean awaitUninterruptibly(long l, TimeUnit timeUnit) {
        return false;
    }

    @Override
    public boolean awaitUninterruptibly(long l) {
        return false;
    }

    @Override
    public Void getNow() {
        return null;
    }

    @Override
    public boolean cancel(boolean b) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Void get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Void get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }

    @Override
    public boolean isVoid() {
        return false;
    }

    @Override
    public ChannelPromise unvoid() {
        return null;
    }
}
