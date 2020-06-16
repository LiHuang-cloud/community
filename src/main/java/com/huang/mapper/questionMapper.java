package com.huang.mapper;

import com.huang.model.question;
import com.huang.model.questionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface questionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    long countByExample(questionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    int deleteByExample(questionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    int insert(question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    int insertSelective(question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    List<question> selectByExampleWithBLOBsWithRowbounds(questionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    List<question> selectByExampleWithBLOBs(questionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    List<question> selectByExampleWithRowbounds(questionExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    List<question> selectByExample(questionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    question selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    int updateByExampleSelective(@Param("record") question record, @Param("example") questionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    int updateByExampleWithBLOBs(@Param("record") question record, @Param("example") questionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    int updateByExample(@Param("record") question record, @Param("example") questionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    int updateByPrimaryKeySelective(question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    int updateByPrimaryKeyWithBLOBs(question record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table QUESTION
     *
     * @mbg.generated Tue Jun 16 12:19:11 CST 2020
     */
    int updateByPrimaryKey(question record);
}