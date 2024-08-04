package ro.itschool.testcontroller.controller;


public record ResponsePayload<T>(T data, String message) {
}
