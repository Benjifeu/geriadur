package com.example.geriadur.service.game.api;

import com.example.geriadur.dto.GameSessionStep;
import com.example.geriadur.dto.StatisticDTO;

import java.util.List;

public interface ISessionGameService {
    List<GameSessionStep> get15GameSessionStep(int wordTheme);
}
