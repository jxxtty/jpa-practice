package com.example.jpapractice.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class IdxListDto {
    private List<Long> idList;
}
