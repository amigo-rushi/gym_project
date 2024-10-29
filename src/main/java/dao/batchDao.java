package dao;

import java.util.List;

import model.Batch;

public interface batchDao {
	void addBatch(Batch batch);
    Batch getBatch(int id);
    void updateBatch(Batch batch);
    void deleteBatch(int id);
    List<Batch> getAllBatches();

}
