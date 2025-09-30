package com.neurobridge.emotisync.servicesinterfaces;

import com.neurobridge.emotisync.entities.CrisisSintoma;
import java.util.List;

public interface ICrisisSintomaService {
    List<CrisisSintoma> list();
    void insert(CrisisSintoma cs);
    CrisisSintoma listId(int id);
    void delete(int id);
    void update(CrisisSintoma cs);
}
