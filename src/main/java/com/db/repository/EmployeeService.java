package com.db.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.db.entity.Employee;

@Repository
public class EmployeeService {
    
    @Autowired
    private DynamoDBMapper dbMapper;

    public Employee save(Employee employee){
        dbMapper.save(employee);
        return employee;
    }

    public Employee getEmployeeById(String id){
        return dbMapper.load(Employee.class,id);
    }

    public String delete(String id){
        Employee employee = dbMapper.load(Employee.class,id);
        dbMapper.delete(employee);
        return "employee deleted!!!!";
    }

    public String update(Employee employee,String id){
        dbMapper.save(employee,
                    new DynamoDBSaveExpression()
                    .withExpectedEntry("id",
                        new ExpectedAttributeValue(
                            new AttributeValue()
                            .withS(id)
                    )));
        return "updated info....";
    }


}
