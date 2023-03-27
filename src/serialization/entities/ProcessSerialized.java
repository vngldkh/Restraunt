package serialization.entities;

import serialization.entities.ProcessElement;

import java.util.ArrayList;

public record ProcessSerialized(ArrayList<ProcessElement> process_log) {
}
