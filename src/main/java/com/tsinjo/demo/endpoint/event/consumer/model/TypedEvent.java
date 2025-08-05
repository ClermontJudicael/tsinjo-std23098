package com.tsinjo.demo.endpoint.event.consumer.model;

import com.tsinjo.demo.PojaGenerated;
import com.tsinjo.demo.endpoint.event.model.PojaEvent;

@PojaGenerated
public record TypedEvent(String typeName, PojaEvent payload) {}
