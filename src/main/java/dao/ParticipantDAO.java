package dao;

import java.util.List;

import model.Participant;

public interface ParticipantDAO {
	void addParticipant(Participant participant);
    Participant getParticipant(int id);
    void updateParticipant(Participant participant);
    void deleteParticipant(int id);
    List<Participant> getAllParticipants();

}
