package developedCode;

public abstract class GenerationStrategy {
	protected int size;
	protected RNG rng;
	
	protected abstract Square[][] generateQueens(Square[][] blankMatrix);
	protected abstract Square[][] assignColorToQueens(Square[][] queenMatrix);
	protected abstract Square[][] createSections(Square[][] coloredMatrix);
	
	public GenerationStrategy(int size, RNG rng) {
		this.size = size;
		this.rng = rng;
	}
	
	protected Square[][] fillBlanks(Square[][] sectionedMatrix){
		return null;
	}
	
	public Square[][] generate(int size, RNG random){
		return null;
	}
	
	
	
//	Remove when finished testing
	public Square[][] callGenerateQueens(Square[][] blankMatrix){
		return generateQueens(blankMatrix);
	}
	
	public Square[][] callAssignColorToQueens(Square[][] queenMatrix){
		return assignColorToQueens(queenMatrix);
	}
	
	public Square[][] callCreateSections(Square[][] coloredMatrix){
		return createSections(coloredMatrix);
	}
}
