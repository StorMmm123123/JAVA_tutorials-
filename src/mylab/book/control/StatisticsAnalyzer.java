package mylab.book.control;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import mylab.book.entity.Magazine;
import mylab.book.entity.Novel;
import mylab.book.entity.Publication;
import mylab.book.entity.ReferenceBook;

public class StatisticsAnalyzer {
    public Map<String, Double> calculateAveragePriceByType(Publication[] publications) {
        Map<String, Integer> priceSums = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();
        Map<String, Double> averages = new HashMap<>();

        for (Publication pub : publications) {
            String type = getPublicationType(pub);
            priceSums.put(type, priceSums.getOrDefault(type, 0) + pub.getPrice());
            counts.put(type, counts.getOrDefault(type, 0) + 1);
        }

        for (String type : priceSums.keySet()) {
            averages.put(type, (double) priceSums.get(type) / counts.get(type));
        }
        return averages;
    }

    public Map<String, Double> calculatePublicationDistribution(Publication[] publications) {
        Map<String, Integer> counts = new HashMap<>();
        Map<String, Double> distribution = new HashMap<>();

        for (Publication pub : publications) {
            String type = getPublicationType(pub);
            counts.put(type, counts.getOrDefault(type, 0) + 1);
        }

        for (String type : counts.keySet()) {
            double percentage = publications.length == 0 ? 0.0
                    : (double) counts.get(type) / publications.length * 100;
            distribution.put(type, percentage);
        }
        return distribution;
    }

    public double calculatePublicationRatioByYear(Publication[] publications, String year) {
        if (publications.length == 0) {
            return 0.0;
        }

        int count = 0;
        for (Publication pub : publications) {
            String date = pub.getPublishDate();
            if (date != null && date.length() >= 4 && date.substring(0, 4).equals(year)) {
                count++;
            }
        }
        return (double) count / publications.length * 100;
    }

    private String getPublicationType(Publication pub) {
        if (pub instanceof Novel) {
            return "소설";
        } else if (pub instanceof Magazine) {
            return "잡지";
        } else if (pub instanceof ReferenceBook) {
            return "참고서";
        }
        return "기타";
    }

    public void printStatistics(Publication[] publications) {
        Map<String, Double> averages = calculateAveragePriceByType(publications);
        Map<String, Double> distribution = calculatePublicationDistribution(publications);
        DecimalFormat priceFormat = new DecimalFormat("#,##0");
        DecimalFormat percentFormat = new DecimalFormat("0.00");

        System.out.println("===== 출판물 통계 분석 =====");
        System.out.println("1. 타입별 평균 가격:");
        for (String type : new String[] { "소설", "참고서", "잡지" }) {
            if (averages.containsKey(type)) {
                System.out.println("   - " + type + ": "
                        + priceFormat.format(averages.get(type)) + "원");
            }
        }
        System.out.println();

        System.out.println("2. 출판물 유형 분포:");
        for (String type : new String[] { "소설", "참고서", "잡지" }) {
            if (distribution.containsKey(type)) {
                System.out.println("   - " + type + ": "
                        + percentFormat.format(distribution.get(type)) + "%");
            }
        }
        System.out.println();

        System.out.println("3. 2007년에 출판된 출판물 비율: "
                + percentFormat.format(calculatePublicationRatioByYear(publications, "2007")) + "%");
    }
}
