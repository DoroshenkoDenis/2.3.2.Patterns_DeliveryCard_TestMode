package ru.netology.web.data;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
@Value
public class UserData {
    String login;
    String password;
    String status;
}
