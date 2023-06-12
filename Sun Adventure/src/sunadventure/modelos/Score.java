package sunadventure.modelos;

public class Score extends Thread {
	private FaseP1 fase;
    private String score;

    public Score(FaseP1 fase) {
    	this.fase = fase;
        this.score = "000000";
    }
    
    public String getScore() {
    	return score;
    }

    @Override
    public void run() {
        while (fase.isEmJogo() == true) {
            try {
                Thread.sleep(1000); // Aumenta o score a cada segundo
                incrementScore();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private synchronized void incrementScore() {
        int currentScore = Integer.parseInt(score);
        currentScore++;
        score = String.format("%06d", currentScore);
    }


  }

