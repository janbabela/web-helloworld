package assembler;

import dto.PositionDto;
import model.PositionModel;

import java.util.ArrayList;
import java.util.List;

public class PositionAssembler {

  public List<PositionModel> extractPositionModels(List<PositionDto> game) {

    List<PositionModel> result = new ArrayList<>();

    game.forEach(p -> result.add(extractPositionModel(p)));

    return result;
  }


  public PositionModel extractPositionModel(PositionDto positionDto) {

    PositionModel positionModel = new PositionModel();
    positionModel.setMoveNumber(positionDto.getMoveNumber());
    positionModel.setLastMove(positionDto.getLastMove());
    positionModel.setEvaluation(positionDto.getEvaluation());
    positionModel.getPositionDescription()[0] = positionDto.getOOpenDoubles();
    positionModel.getPositionDescription()[1] = positionDto.getOMostlyOpenDoubles();
    positionModel.getPositionDescription()[2] = positionDto.getOHalfOpenDoubles();
    positionModel.getPositionDescription()[3] = positionDto.getOOpenDisconnectedDoubles();
    positionModel.getPositionDescription()[4] = positionDto.getOMostlyOpenDisconnectedDoubles();
    positionModel.getPositionDescription()[5] = positionDto.getOHalfOpenDisconnectedDoubles();
    positionModel.getPositionDescription()[6] = positionDto.getOOpenTriples();
    positionModel.getPositionDescription()[7] = positionDto.getOMostlyOpenTriples();
    positionModel.getPositionDescription()[8] = positionDto.getOHalfOpenTriples();
    positionModel.getPositionDescription()[9] = positionDto.getOOpenDisconnectedTriples();
    positionModel.getPositionDescription()[10] = positionDto.getOHalfOpenDisconnectedTriples();
    positionModel.getPositionDescription()[11] = positionDto.getOTwiceDisconnectedTriples();
    positionModel.getPositionDescription()[12] = positionDto.getOOpenQuadruples();
    positionModel.getPositionDescription()[13] = positionDto.getOHalfOpenQuadruples();
    positionModel.getPositionDescription()[14] = positionDto.getODisconnectedQuadruples();
    positionModel.getPositionDescription()[15] = positionDto.getOQuintuples();
    positionModel.getPositionDescription()[16] = positionDto.getXOpenDoubles();
    positionModel.getPositionDescription()[17] = positionDto.getXMostlyOpenDoubles();
    positionModel.getPositionDescription()[18] = positionDto.getXHalfOpenDoubles();
    positionModel.getPositionDescription()[19] = positionDto.getXOpenDisconnectedDoubles();
    positionModel.getPositionDescription()[20] = positionDto.getXMostlyOpenDisconnectedDoubles();
    positionModel.getPositionDescription()[21] = positionDto.getXHalfOpenDisconnectedDoubles();
    positionModel.getPositionDescription()[22] = positionDto.getXOpenTriples();
    positionModel.getPositionDescription()[23] = positionDto.getXMostlyOpenTriples();
    positionModel.getPositionDescription()[24] = positionDto.getXHalfOpenTriples();
    positionModel.getPositionDescription()[25] = positionDto.getXOpenDisconnectedTriples();
    positionModel.getPositionDescription()[26] = positionDto.getXHalfOpenDisconnectedTriples();
    positionModel.getPositionDescription()[27] = positionDto.getXTwiceDisconnectedTriples();
    positionModel.getPositionDescription()[28] = positionDto.getXOpenQuadruples();
    positionModel.getPositionDescription()[29] = positionDto.getXHalfOpenQuadruples();
    positionModel.getPositionDescription()[30] = positionDto.getXDisconnectedQuadruples();
    positionModel.getPositionDescription()[31] = positionDto.getXQuintuples();

    return positionModel;
  }

  public PositionDto populatePositionDto(PositionModel positionModel) {

    PositionDto positionDto = new PositionDto();
    positionDto.setMoveNumber(positionModel.getMoveNumber());
    positionDto.setLastMove(positionModel.getLastMove());
    positionDto.setEvaluation(positionModel.getEvaluation());
    positionDto.setOOpenDoubles(positionModel.getPositionDescription()[0]);
    positionDto.setOMostlyOpenDoubles(positionModel.getPositionDescription()[1]);
    positionDto.setOHalfOpenDoubles(positionModel.getPositionDescription()[2]);
    positionDto.setOOpenDisconnectedDoubles(positionModel.getPositionDescription()[3]);
    positionDto.setOMostlyOpenDisconnectedDoubles(positionModel.getPositionDescription()[4]);
    positionDto.setOHalfOpenDisconnectedDoubles(positionModel.getPositionDescription()[5]);
    positionDto.setOOpenTriples(positionModel.getPositionDescription()[6]);
    positionDto.setOMostlyOpenTriples(positionModel.getPositionDescription()[7]);
    positionDto.setOHalfOpenTriples(positionModel.getPositionDescription()[8]);
    positionDto.setOOpenDisconnectedTriples(positionModel.getPositionDescription()[9]);
    positionDto.setOHalfOpenDisconnectedTriples(positionModel.getPositionDescription()[10]);
    positionDto.setOTwiceDisconnectedTriples(positionModel.getPositionDescription()[11]);
    positionDto.setOOpenQuadruples(positionModel.getPositionDescription()[12]);
    positionDto.setOHalfOpenQuadruples(positionModel.getPositionDescription()[13]);
    positionDto.setODisconnectedQuadruples(positionModel.getPositionDescription()[14]);
    positionDto.setOQuintuples(positionModel.getPositionDescription()[15]);
    positionDto.setXOpenDoubles(positionModel.getPositionDescription()[16]);
    positionDto.setXMostlyOpenDoubles(positionModel.getPositionDescription()[17]);
    positionDto.setXHalfOpenDoubles(positionModel.getPositionDescription()[18]);
    positionDto.setXOpenDisconnectedDoubles(positionModel.getPositionDescription()[19]);
    positionDto.setXMostlyOpenDisconnectedDoubles(positionModel.getPositionDescription()[20]);
    positionDto.setXHalfOpenDisconnectedDoubles(positionModel.getPositionDescription()[21]);
    positionDto.setXOpenTriples(positionModel.getPositionDescription()[22]);
    positionDto.setXMostlyOpenTriples(positionModel.getPositionDescription()[23]);
    positionDto.setXHalfOpenTriples(positionModel.getPositionDescription()[24]);
    positionDto.setXOpenDisconnectedTriples(positionModel.getPositionDescription()[25]);
    positionDto.setXHalfOpenDisconnectedTriples(positionModel.getPositionDescription()[26]);
    positionDto.setXTwiceDisconnectedTriples(positionModel.getPositionDescription()[27]);
    positionDto.setXOpenQuadruples(positionModel.getPositionDescription()[28]);
    positionDto.setXHalfOpenQuadruples(positionModel.getPositionDescription()[29]);
    positionDto.setXDisconnectedQuadruples(positionModel.getPositionDescription()[30]);
    positionDto.setXQuintuples(positionModel.getPositionDescription()[31]);
    
    return positionDto;
  }

}
