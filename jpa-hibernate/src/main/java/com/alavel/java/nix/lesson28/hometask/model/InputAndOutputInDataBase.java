package com.alavel.java.nix.lesson28.hometask.model;


import com.alavel.java.nix.lesson28.hometask.entity.City;
import com.alavel.java.nix.lesson28.hometask.entity.Connection;
import com.alavel.java.nix.lesson28.hometask.entity.Problem;
import com.alavel.java.nix.lesson28.hometask.entity.Solution;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;


public class InputAndOutputInDataBase {

    private final Map<String, Long> cityIndexByName = new LinkedHashMap<>();
    private int size;
    private long[][] cityNeighbors;
    private long[][] cityNeighborCosts;
    private List<Vertex> peaks;
    private final Logger log = LoggerFactory.getLogger(InputAndOutputInDataBase.class);


    public void loadData(Session session) {
        Transaction transaction = session.beginTransaction();
        try {


            Query<City> cityList = session.createQuery("from City", City.class);
            List<City> cities = cityList.getResultList();
            for (City city : cities) {
                cityIndexByName.put(city.getName(), city.getId());
            }
            size = cityIndexByName.size();
            cityNeighbors = new long[size][];
            cityNeighborCosts = new long[size][];

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Connection> cr = cb.createQuery(Connection.class);
            Root<Connection> root = cr.from(Connection.class);


            for (Long i = 1L; i <= size; i++) {
                CriteriaQuery<Connection> criteriaQuery = cr.where(cb.equal(root.get("from"), i));
                List<Connection> connections = session.createQuery(criteriaQuery).getResultList();
                int countRows = connections.size();
                long[] neighborIndices = cityNeighbors[i.intValue() - 1] = new long[countRows];
                long[] neighborCosts = cityNeighborCosts[i.intValue() - 1] = new long[countRows];
                for (int j = 0; j < countRows; j++) {
                    neighborIndices[j] = connections.get(j).getTo().getId();
                    neighborCosts[j] = connections.get(j).getCost();
                }
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            log.error("Error during transaction", e);
        }

    }

    public void createVertexes() {
        peaks = new ArrayList<>(size);
        for (Map.Entry<String, Long> name : cityIndexByName.entrySet()) {
            peaks.add(new Vertex(name.getKey()));
        }
        for (int j = 0; j < peaks.size(); j++) {
            Vertex peak = peaks.get(j);
            for (int i = 0; i < cityNeighbors[j].length; i++) {
                Vertex vertex = peaks.get((int) (cityNeighbors[j][i]) - 1);
                peak.adjacencies.add(new Edge(vertex, (int) cityNeighborCosts[j][i]));
            }
        }
    }

    public void output(Session session){
        Transaction tx = session.beginTransaction();
        long minDistance;
        try {
            List<Problem> problemList = session.createQuery(
                    "from Problem", Problem.class)
                    .getResultList();
            for(Problem problem :problemList){
                Dijkstra.computePaths(peaks.get(problem.getFrom().getId().intValue()-1));
                minDistance = peaks.get(problem.getTo().getId().intValue()-1).minDistance;
                Solution solution = new Solution();
                solution.setProblem(problem);
                solution.setProblemId(problem.getId());
                solution.setMinCost(minDistance);
                session.save(solution);
            }
            tx.commit();
        }catch (Exception e) {
            tx.rollback();
            log.error("Error during transaction", e);
        }
    }


}

