public class MinMaxResult {
    private int evaluation;
    private MoveType bestMove;

    public MinMaxResult(int evaluation, MoveType bestMove) {
        this.evaluation = evaluation;
        this.bestMove = bestMove;
    }

    public int getEvaluation() {
        return evaluation;
    }

    public MoveType getBestMove() {
        return bestMove;
    }

    @Override
    public String toString() {
        return "MinMaxResult{" +
                "evaluation=" + evaluation +
                ", bestMove=" + bestMove +
                '}';
    }
}
