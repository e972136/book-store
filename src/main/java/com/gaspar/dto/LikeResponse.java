package com.gaspar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class LikeResponse {
     Integer bookId;
     Set<String> customersEmail;
     Integer likes;
}
