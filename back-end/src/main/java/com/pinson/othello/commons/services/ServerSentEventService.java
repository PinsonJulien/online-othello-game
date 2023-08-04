package com.pinson.othello.commons.services;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServerSentEventService<T> {

    private final List<SseEmitter> emitters;
    private final Map<Long, List<SseEmitter>> indexedEmitters;

    public ServerSentEventService() {
        this.emitters = new CopyOnWriteArrayList<>();
        this.indexedEmitters = new ConcurrentHashMap<>();
    }

    public SseEmitter add(SseEmitter emitter) {
        this.emitters.add(emitter);

        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> {
            emitter.complete();
            this.emitters.remove(emitter);
        });

        return emitter;
    }

    public SseEmitter add(SseEmitter emitter, Long index) {
        if (!this.indexedEmitters.containsKey(index))
            this.indexedEmitters.put(index, new CopyOnWriteArrayList<>());

        this.indexedEmitters.get(index).add(emitter);

        emitter.onCompletion(() -> this.indexedEmitters.get(index).remove(emitter));

        emitter.onTimeout(() -> {
            emitter.complete();
            this.indexedEmitters.get(index).remove(emitter);
        });

        return emitter;
    }

    public void send(T data) {
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(data);
            } catch (Exception e) {
                emitter.completeWithError(e);
                this.emitters.remove(emitter);
            }
        });
    }

    public void send(T data, Long index) {
        List<SseEmitter> emitters = this.indexedEmitters.get(index);

        emitters.forEach(emitter -> {
            try {
                emitter.send(data);
            } catch (Exception e) {
                emitter.completeWithError(e);
                emitters.remove(emitter);
            }
        });
    }
}
