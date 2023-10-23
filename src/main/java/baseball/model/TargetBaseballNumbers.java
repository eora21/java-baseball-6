package baseball.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TargetBaseballNumbers {
    private final List<BaseballNumber> baseballNumbers;

    private TargetBaseballNumbers(final List<BaseballNumber> baseballNumbers) {
        this.baseballNumbers = baseballNumbers;
    }

    public static TargetBaseballNumbers randomInstance(final int size) {
        List<BaseballNumber> uniqueBaseballNumbers = new ArrayList<>();

        while (uniqueBaseballNumbers.size() < size) {
            BaseballNumber randomBaseballNumber = BaseballNumber.createRandomNumber();

            if (uniqueBaseballNumbers.contains(randomBaseballNumber)) {
                continue;
            }

            uniqueBaseballNumbers.add(randomBaseballNumber);
        }

        return new TargetBaseballNumbers(Collections.unmodifiableList(uniqueBaseballNumbers));
    }

    public GameResult calculateGameResult(final List<Integer> inputNumbers) {
        List<BaseballNumber> playerBaseballNumbers = convertInputToBaseballNumbers(inputNumbers);

        if (baseballNumbers.size() != playerBaseballNumbers.size()) {
            throw new IllegalArgumentException();
        }

        return createGameResult(playerBaseballNumbers);
    }

    private List<BaseballNumber> convertInputToBaseballNumbers(final List<Integer> inputNumbers) {
        verifyDuplicates(inputNumbers);

        return inputNumbers.stream()
                .map(BaseballNumber::new)
                .toList();
    }

    private void verifyDuplicates(final List<Integer> inputPlayerNumbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(inputPlayerNumbers);

        if (uniqueNumbers.size() != inputPlayerNumbers.size()) {
            throw new IllegalArgumentException();
        }
    }

    private GameResult createGameResult(List<BaseballNumber> playerBaseballNumbers) {
        int correctCount = 0;
        int similarCount = 0;

        for (int i = 0; i < baseballNumbers.size(); i++) {
            BaseballNumber computerBaseballNumber = baseballNumbers.get(i);
            BaseballNumber playerBaseballNumber = playerBaseballNumbers.get(i);

            if (computerBaseballNumber.equals(playerBaseballNumber)) {
                correctCount++;
                continue;
            }

            if (baseballNumbers.contains(playerBaseballNumber)) {
                similarCount++;
            }
        }

        return new GameResult(correctCount, similarCount);
    }
}
