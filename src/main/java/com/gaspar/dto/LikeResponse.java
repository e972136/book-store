package com.gaspar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class LikeResponse {
     Integer bookId;
     List<String> customersEmail;
     Integer likes;
}
